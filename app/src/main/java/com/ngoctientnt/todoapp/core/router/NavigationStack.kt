package com.ngoctientnt.todoapp.core.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ngoctientnt.todoapp.presentation.home_intro.HomeIntroScreen
import com.ngoctientnt.todoapp.presentation.on_boarding.OnBoardingScreen
import com.ngoctientnt.todoapp.presentation.sign_in.SignInScreen
import com.ngoctientnt.todoapp.presentation.sign_up.SignUpScreen
import com.ngoctientnt.todoapp.presentation.welcome.WelcomeScreen

@Composable
fun NavigationStack(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.OnBoarding.route
    ) {
        composable(ScreenRoute.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(ScreenRoute.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(ScreenRoute.SignIn.route) {
            SignInScreen(navController)
        }
        composable(ScreenRoute.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(ScreenRoute.HomeIntro.route) {
            HomeIntroScreen(navController)
        }
    }
}