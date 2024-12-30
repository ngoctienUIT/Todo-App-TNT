package com.ngoctientnt.todoapp.presentation.sign_in

data class SignInState(
    val email: String = "",
    val emailErrMsg: String? = null,
    val password: String = "",
    val passwordErrMsg: String? = null,
    val passwordVisible: Boolean = false,
)
