package com.jcasben.jetpackcomponentscatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun Screen1(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(text = "Screen 1",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Screen2(name = "Jesus", age = 20)) })
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(text = "Screen 2",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Screen3) })
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text = "Screen 3",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Screen1) })
    }
}