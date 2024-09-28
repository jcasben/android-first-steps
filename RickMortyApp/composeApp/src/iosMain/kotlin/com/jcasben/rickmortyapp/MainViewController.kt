package com.jcasben.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jcasben.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }