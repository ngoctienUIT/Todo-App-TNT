package com.ngoctientnt.todoapp.core.router

enum class Screen {
    ONBOARDING,
    SIGNIN,
    SIGNUP,
    HOMEINTRO,
}

sealed class ScreenRoute(val route: String) {
    data object OnBoarding : ScreenRoute(Screen.ONBOARDING.name)
    data object SignIn : ScreenRoute(Screen.SIGNIN.name)
    data object SignUp : ScreenRoute(Screen.SIGNUP.name)
    data object HomeIntro : ScreenRoute(Screen.HOMEINTRO.name)
}
