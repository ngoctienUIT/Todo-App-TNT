package com.ngoctientnt.todoapp.domain.usecase

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.ngoctientnt.todoapp.core.resource.Response
import com.ngoctientnt.todoapp.core.resource.UseCase
import com.ngoctientnt.todoapp.domain.repository.AuthRepository
import javax.inject.Inject

class OneTapSignInUseCase @Inject constructor(val repository: AuthRepository) :
    UseCase<Response<BeginSignInResult>, Unit> {
    override suspend fun invoke(param: Unit): Response<BeginSignInResult> {
        return repository.onTapSignIn()
    }
}