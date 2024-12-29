package com.ngoctientnt.todoapp.core.extension

import androidx.navigation.NavController

fun NavController.replace(route: String) {
    val currentRoute = currentBackStackEntry?.destination?.route
    navigate(route) {
        if (currentRoute != null) popUpTo(currentRoute) { inclusive = true }
    }
}