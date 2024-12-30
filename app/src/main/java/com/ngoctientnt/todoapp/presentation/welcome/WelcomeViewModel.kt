package com.ngoctientnt.todoapp.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.ngoctientnt.todoapp.core.resource.Response
import com.ngoctientnt.todoapp.core.resource.UIState
import com.ngoctientnt.todoapp.domain.usecase.OneTapSignInUseCase
import com.ngoctientnt.todoapp.domain.usecase.SignInGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val oneTapSignInUseCase: OneTapSignInUseCase,
    val oneTapClient: SignInClient
) : ViewModel() {
    private val _oneTapSignInState = MutableStateFlow<UIState<BeginSignInResult>>(UIState.Init())
    val oneTapSignInState = _oneTapSignInState.asStateFlow()

    private val _signInState = MutableStateFlow<UIState<Unit>>(UIState.Init())
    val signInState = _signInState.asStateFlow()

    fun signInWithGoogle(credential: SignInCredential) {
        viewModelScope.launch {
            _signInState.update { UIState.Loading() }
            val response = signInGoogleUseCase.invoke(credential)
            if (response.isSuccess) {
                _signInState.update { UIState.Success(Unit) }
            } else {
                _signInState.update { UIState.Error(response.message ?: "") }
            }
        }
    }

    fun onTapSignIn() {
        viewModelScope.launch {
            _oneTapSignInState.update { UIState.Loading() }
            val response = oneTapSignInUseCase.invoke(Unit)
            if (response.isSuccess) {
                if (response.data != null)
                    _oneTapSignInState.update { UIState.Success(response.data) }
            } else {
                _oneTapSignInState.update { UIState.Error(response.message ?: "") }
            }
        }
    }
}
