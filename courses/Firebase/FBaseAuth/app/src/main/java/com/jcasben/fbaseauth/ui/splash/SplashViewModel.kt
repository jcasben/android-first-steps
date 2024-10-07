package com.jcasben.fbaseauth.ui.splash

import androidx.lifecycle.ViewModel
import com.jcasben.fbaseauth.data.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    private fun isUserLogged(): Boolean {
        return authService.isUserLogged()
    }

    fun checkDestination(): SplashDestination {
        val isUserLogged = isUserLogged()
        return if (isUserLogged) {
            SplashDestination.Home
        } else {
            SplashDestination.Login
        }
    }
}

sealed class SplashDestination {
    data object Login: SplashDestination()
    data object Home: SplashDestination()
}