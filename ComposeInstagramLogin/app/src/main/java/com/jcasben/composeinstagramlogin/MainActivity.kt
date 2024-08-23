package com.jcasben.composeinstagramlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.jcasben.composeinstagramlogin.login.ui.LoginScreen
import com.jcasben.composeinstagramlogin.login.ui.LoginViewModel
import com.jcasben.composeinstagramlogin.ui.theme.ComposeInstagramLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInstagramLoginTheme {
                LoginScreen(loginViewModel)
            }
        }
    }
}