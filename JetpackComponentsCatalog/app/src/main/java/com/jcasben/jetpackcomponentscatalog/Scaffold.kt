package com.jcasben.jetpackcomponentscatalog

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldExample() {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet { MyDrawer { coroutineScope.launch { drawerState.close() } } }
        }, drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                MyTopAppBar(onIconClick = {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("You clicked: $it")
                    }
                }, onDrawerClick = {
                    coroutineScope.launch { drawerState.open() }
                })
            },
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            bottomBar = { MyBottomNavigation() },
            floatingActionButton = { MyFAB() },
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onIconClick: (String) -> Unit, onDrawerClick: () -> Unit) {
    TopAppBar(title = { Text(text = "First ToolBar") }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Blue,
        titleContentColor = Color.White,
        actionIconContentColor = Color.White,
        navigationIconContentColor = Color.White
    ), navigationIcon = {
        IconButton(onClick = { onDrawerClick() }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
            )
        }
    }, actions = {
        IconButton(onClick = { onIconClick("Search") }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "back",
            )
        }
    })
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableStateOf(0) }

    NavigationBar(containerColor = Color.Blue) {
        NavigationBarItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(
                imageVector = Icons.Filled.Home, contentDescription = "home"
            )
        }, label = { Text(text = "Home") }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Blue,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            selectedIconColor = Color.White,
            selectedTextColor = Color.White
        )
        )
        NavigationBarItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(
                imageVector = Icons.Filled.Favorite, contentDescription = "fav"
            )
        }, label = { Text(text = "Favourite") }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Blue,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            selectedIconColor = Color.White,
            selectedTextColor = Color.White
        )
        )
        NavigationBarItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(
                imageVector = Icons.Filled.Person, contentDescription = "profile"
            )
        }, label = { Text(text = "Profile") }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Blue,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            selectedIconColor = Color.White,
            selectedTextColor = Color.White
        )
        )
    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(onClick = { }, containerColor = Color.Magenta) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Option 1",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Option 2",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Option 3",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Option 4",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Option 5",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
    }
}
