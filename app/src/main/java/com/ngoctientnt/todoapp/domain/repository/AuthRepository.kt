package com.ngoctientnt.todoapp.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInCredential
import com.ngoctientnt.todoapp.core.resource.Response
import com.ngoctientnt.todoapp.data.model.User
import com.ngoctientnt.todoapp.domain.model.SignInModel
import com.ngoctientnt.todoapp.domain.model.SignUpModel

interface AuthRepository {
    suspend fun signIn(data: SignInModel): Response<String>

    suspend fun signInWithGoogle(credential: SignInCredential): Response<String>

    suspend fun signUp(data: SignUpModel): Response<User>

    suspend fun getUserInfo(uuid: String): Response<User>

    suspend fun onTapSignIn(): Response<BeginSignInResult>
}