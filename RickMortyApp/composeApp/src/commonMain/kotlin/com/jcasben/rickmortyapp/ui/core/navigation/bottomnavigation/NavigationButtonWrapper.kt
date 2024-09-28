package com.jcasben.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jcasben.rickmortyapp.ui.core.navigation.Routes
import com.jcasben.rickmortyapp.ui.home.tabs.characters.CharactersScreen
import com.jcasben.rickmortyapp.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationButtonWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(route = Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(route = Routes.Characters.route) {
            CharactersScreen()
        }
    }

}