package com.ngoctientnt.todoapp.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngoctientnt.todoapp.core.extension.DataStoreManager
import com.ngoctientnt.todoapp.core.extension.validateEmail
import com.ngoctientnt.todoapp.core.resource.UIState
import com.ngoctientnt.todoapp.domain.model.SignInModel
import com.ngoctientnt.todoapp.domain.usecase.GetUserInfoUseCase
import com.ngoctientnt.todoapp.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _uiState = MutableStateFlow<UIState<Unit>>(UIState.Init())
    val uiState = _uiState.asStateFlow()

    fun onChangePasswordVisibility() {
        _signInState.update {
            it.copy(passwordVisible = !it.passwordVisible)
        }
    }

    fun onChangePasswordValue(value: String) {
        val emailErrMsg = if (value.isEmpty()) {
            "Password is required"
        } else {
            null
        }
        _signInState.update {
            it.copy(password = value, emailErrMsg = emailErrMsg)
        }
    }

    fun onChangeEmailValue(value: String) {
        val emailErrMsg = if (value.isEmpty()) {
            "Email is required"
        } else if (!value.validateEmail()) {
            "Email is invalid"
        } else {
            null
        }
        _signInState.update {
            it.copy(email = value, emailErrMsg = emailErrMsg)
        }
    }

    fun onSignIn() {
        viewModelScope.launch {
            _uiState.update { UIState.Loading() }
            val response =
                signInUseCase.invoke(
                    SignInModel(
                        signInState.value.email,
                        signInState.value.password
                    )
                )
            if (response.isSuccess) {
                if (response.data != null) getUserInfo(response.data)
                _uiState.update { UIState.Success(Unit) }
            } else {
                _uiState.update { UIState.Error(response.message ?: "") }
            }
        }
    }

    private fun getUserInfo(uuid: String) {
        viewModelScope.launch {
            val response = getUserInfoUseCase.invoke(uuid)
            dataStoreManager.setUser(response.data)
        }
    }
}