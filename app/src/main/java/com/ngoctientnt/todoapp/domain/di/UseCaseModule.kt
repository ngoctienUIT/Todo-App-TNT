package com.ngoctientnt.todoapp.domain.di

import com.ngoctientnt.todoapp.domain.repository.AuthRepository
import com.ngoctientnt.todoapp.domain.usecase.GetUserInfoUseCase
import com.ngoctientnt.todoapp.domain.usecase.OneTapSignInUseCase
import com.ngoctientnt.todoapp.domain.usecase.SignInGoogleUseCase
import com.ngoctientnt.todoapp.domain.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun signInUseCaseProvider(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun getUserInfoUseCaseProvider(authRepository: AuthRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun signInGoogleUseCaseProvider(authRepository: AuthRepository): SignInGoogleUseCase {
        return SignInGoogleUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun oneTapSignInUseCaseProvider(authRepository: AuthRepository): OneTapSignInUseCase {
        return OneTapSignInUseCase(authRepository)
    }
}