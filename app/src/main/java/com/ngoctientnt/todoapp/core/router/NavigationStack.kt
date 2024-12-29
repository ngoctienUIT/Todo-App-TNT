package com.ngoctientnt.todoapp.core.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ngoctientnt.todoapp.presentation.home_intro.HomeIntroScreen
import com.ngoctientnt.todoapp.presentation.on_boarding.OnBoardingScreen
import com.ngoctientnt.todoapp.presentation.sign_in.SignInScreen
import com.ngoctientnt.todoapp.presentation.sign_up.SignUpScreen

@Composable
fun NavigationStack(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.OnBoarding
    ) {
        composable(ScreenRoute.OnBoarding.route) {
            OnBoardingScreen(navController)
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