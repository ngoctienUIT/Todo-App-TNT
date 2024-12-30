package com.ngoctientnt.todoapp.core.resource

sealed class UIState<T>(val data: T? = null, val message: String? = null) {
    class Init<T> : UIState<T>()
    class Success<T>(data: T) : UIState<T>(data)
    class Error<T>(message: String) : UIState<T>(message = message)
    class Loading<T> : UIState<T>()

    val isSuccess: Boolean
        get() = this is Success

    val isError: Boolean
        get() = this is Error

    val isLoading: Boolean
        get() = this is Loading
}