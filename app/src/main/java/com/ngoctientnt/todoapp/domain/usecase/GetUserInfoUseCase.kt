package com.ngoctientnt.todoapp.domain.usecase

import com.ngoctientnt.todoapp.core.resource.Response
import com.ngoctientnt.todoapp.core.resource.UseCase
import com.ngoctientnt.todoapp.data.model.User
import com.ngoctientnt.todoapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(val repository: AuthRepository) :
    UseCase<Response<User>, String> {
    override suspend fun invoke(param: String): Response<User> {
        return repository.getUserInfo(param)
    }
}