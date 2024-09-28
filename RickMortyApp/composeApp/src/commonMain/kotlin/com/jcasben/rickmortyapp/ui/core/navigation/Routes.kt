package com.jcasben.rickmortyapp.ui.core.navigation

sealed class Routes(val route: String) {
    data object Home: Routes("home")

    // Home bottom nav
    data object Episodes: Routes("episodes")
    data object Characters: Routes("characters")

}