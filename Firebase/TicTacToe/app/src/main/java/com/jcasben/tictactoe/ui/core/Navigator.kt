package com.jcasben.tictactoe.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jcasben.tictactoe.ui.core.Routes.*
import com.jcasben.tictactoe.ui.game.GameScreen
import com.jcasben.tictactoe.ui.home.HomeScreen

@Composable
fun NavigationWrapper(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        startDestination = Home.route
    ) {
        composable(Home.route) {
            HomeScreen() { navigationController.navigate(Game.route) }
        }
        composable(Game.route) {
            GameScreen()
        }
    }
}

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Game : Routes("game")
}