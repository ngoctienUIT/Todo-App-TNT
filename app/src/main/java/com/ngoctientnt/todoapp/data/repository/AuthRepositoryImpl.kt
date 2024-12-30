package com.ngoctientnt.todoapp.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.ngoctientnt.todoapp.core.resource.*
import com.ngoctientnt.todoapp.core.utils.Constants
import com.ngoctientnt.todoapp.data.model.User
import com.ngoctientnt.todoapp.domain.model.SignInModel
import com.ngoctientnt.todoapp.domain.model.SignUpModel
import com.ngoctientnt.todoapp.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private var oneTapClient: SignInClient,
    @Named("signInRequest") private var signInRequest: BeginSignInRequest,
    @Named("signUpRequest") private var signUpRequest: BeginSignInRequest,
) : AuthRepository {
    override suspend fun signIn(data: SignInModel): Response<String> {
        return try {
            val authResult: AuthResult = auth
                .signInWithEmailAndPassword(data.email, data.password)
                .await()
            if (authResult.user != null) {
                Response.Success(authResult.user?.uid ?: "")
            } else {
                Response.Error("User not found")
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun signInWithGoogle(credential: SignInCredential): Response<String> {
        val googleCredential = GoogleAuthProvider
            .getCredential(credential.googleIdToken, null)
        return authenticateUser(googleCredential)
    }

    private suspend fun authenticateUser(credential: AuthCredential): Response<String> {
        val response = if (auth.currentUser != null) authLink(credential)
        else authSignIn(credential)
        if (response.data != null) checkAndRegisterUser(response.data)

        return if (response.isSuccess) Response.Success(response.data?.uuid ?: "")
        else Response.Error(response.message ?: "")
    }

    private suspend fun authLink(credential: AuthCredential): Response<User> {
        return try {
            val authResult = auth.currentUser?.linkWithCredential(credential)?.await()
            val authUser = authResult?.user
            Response.Success(
                User(
                    email = authUser?.email ?: "",
                    uuid = authUser?.uid ?: "",
                    fullName = authUser?.displayName ?: ""
                )
            )
        } catch (error: FirebaseAuthException) {
            when (error.errorCode) {
                Constants.AuthErrors.CREDENTIAL_ALREADY_IN_USE,
                Constants.AuthErrors.EMAIL_ALREADY_IN_USE -> {
                    return authSignIn(credential)
                }
            }
            Response.Error(error.message ?: "")
        } catch (error: Exception) {
            Response.Error(error.message ?: "")
        }
    }

    private suspend fun authSignIn(credential: AuthCredential): Response<User> {
        return try {
            val authResult = auth.signInWithCredential(credential).await()
            val authUser = authResult.user
            Response.Success(
                User(
                    email = authUser?.email ?: "",
                    uuid = authUser?.uid ?: "",
                    fullName = authUser?.displayName ?: ""
                )
            )
        } catch (error: Exception) {
            Response.Error(error.message ?: "")
        }
    }

    override suspend fun signUp(data: SignUpModel): Response<User> {
        return try {
            val authResult: AuthResult = auth
                .createUserWithEmailAndPassword(data.email, data.password)
                .await()
            if (authResult.user != null) {
                registerUser(data, authResult.user?.uid ?: "")
                Response.Success(User(data.fullName, data.email, authResult.user?.uid ?: ""))
            } else {
                Response.Error("User not found")
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun getUserInfo(uuid: String): Response<User> {
        return try {
            val documentSnapshot = firestore.collection("users").document(uuid).get().await()
            val data = documentSnapshot.data

            if (data != null) {
                val stringData = data.mapValues { it.value.toString() }
                Response.Success(User.from(stringData))
            } else {
                Response.Error("User not found")
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun onTapSignIn(): Response<BeginSignInResult> {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Response.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Response.Success(signUpResult)
            } catch (e: Exception) {
                Response.Error(e.message ?: "")
            }
        }
    }

    private suspend fun registerUser(data: SignUpModel, uuid: String) {
        firestore.collection("users").document(uuid).set(
            mapOf(
                "fullName" to data.fullName,
                "email" to data.email,
                "uuid" to uuid
            )
        ).await()
    }

    private suspend fun checkAndRegisterUser(data: User) {
        val response = firestore.collection("users").document(data.uuid).get().await()
        if (response.exists()) return
        registerUser(
            data = SignUpModel(email = data.email, fullName = data.fullName, password = ""),
            uuid = data.uuid
        )
    }
}