package com.jcasben.fbaseauth.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcasben.fbaseauth.data.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    fun logout(navigateToLogin: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            authService.logout()
        }
        navigateToLogin()
    }

    fun getUserEmail(): String {
        return authService.getCurrentUser()?.email.orEmpty()
    }
}
