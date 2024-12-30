package com.ngoctientnt.todoapp.core.resource

interface UseCase<T, P> {
    suspend fun invoke(param: P): T
}