package com.jcasben.firebaselite

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jcasben.firebaselite.presentation.home.HomeScreen
import com.jcasben.firebaselite.presentation.login.LoginScreen
import com.jcasben.firebaselite.presentation.signup.SignUpScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navigateToLogin = { navController.navigate("login") },
                navigateToSignUp = { navController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen()
        }
        composable("signup") {
            SignUpScreen()
        }
    }
}