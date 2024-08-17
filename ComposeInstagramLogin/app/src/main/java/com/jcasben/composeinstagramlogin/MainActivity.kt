package com.jcasben.composeinstagramlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jcasben.composeinstagramlogin.login.ui.LoginScreen
import com.jcasben.composeinstagramlogin.login.ui.LoginViewModel
import com.jcasben.composeinstagramlogin.ui.theme.ComposeInstagramLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInstagramLoginTheme {
                LoginScreen(LoginViewModel())
            }
        }
    }
}