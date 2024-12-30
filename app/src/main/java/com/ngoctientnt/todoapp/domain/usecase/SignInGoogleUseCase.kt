package com.ngoctientnt.todoapp.domain.usecase

import com.google.android.gms.auth.api.identity.SignInCredential
import com.ngoctientnt.todoapp.core.resource.Response
import com.ngoctientnt.todoapp.core.resource.UseCase
import com.ngoctientnt.todoapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignInGoogleUseCase @Inject constructor(private val authRepository: AuthRepository) :
    UseCase<Response<String>, SignInCredential> {
    override suspend fun invoke(param: SignInCredential): Response<String> {
        return authRepository.signInWithGoogle(param)
    }
}