package com.ngoctientnt.todoapp.domain.usecase

import com.ngoctientnt.todoapp.core.resource.Response
import com.ngoctientnt.todoapp.core.resource.UseCase
import com.ngoctientnt.todoapp.domain.model.SignInModel
import com.ngoctientnt.todoapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) :
    UseCase<Response<String>, SignInModel> {
    override suspend fun invoke(param: SignInModel): Response<String> {
        return authRepository.signIn(param)
    }
}