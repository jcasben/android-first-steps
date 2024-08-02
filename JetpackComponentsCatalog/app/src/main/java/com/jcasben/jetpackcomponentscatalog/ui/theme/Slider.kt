package com.jcasben.jetpackcomponentscatalog.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun MyBasicSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var value by rememberSaveable { mutableStateOf(0f) }
        Slider(value = value, onValueChange = { value = it })
        Text(text = value.toString())
    }
}

@Composable
fun MyAdvancedSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var value by rememberSaveable { mutableStateOf(0f) }
        Slider(value = value, onValueChange = { value = it }, valueRange = 0f..100f, steps = 101)
        Text(text = value.toInt().toString())
    }
}