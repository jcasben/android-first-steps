package com.jcasben.rickmortyapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jcasben.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.jcasben.rickmortyapp.ui.core.BackgroundSecondaryColor
import com.jcasben.rickmortyapp.ui.core.BackgroundTertiaryColor
import com.jcasben.rickmortyapp.ui.core.DefaultTextColor
import com.jcasben.rickmortyapp.ui.core.Green
import com.jcasben.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem
import com.jcasben.rickmortyapp.ui.core.navigation.bottomnavigation.NavigationButtonWrapper
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ricktoolbar

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(BottomBarItem.Episodes(), BottomBarItem.Characters())
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController, items) },
        topBar = { TopBar() }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavigationButtonWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController, items: List<BottomBarItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = BackgroundSecondaryColor, contentColor = Green) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    unselectedIconColor = Green
                ),
                icon = item.icon,
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = { Text(text = item.title, color = DefaultTextColor) }
            )
        }
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier.fillMaxWidth().background(BackgroundPrimaryColor),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(Res.drawable.ricktoolbar),
            contentDescription = "Toolbar image",
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp)
        )
    }
}