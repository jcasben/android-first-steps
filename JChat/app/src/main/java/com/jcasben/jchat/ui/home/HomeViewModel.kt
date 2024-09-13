package com.jcasben.jchat.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcasben.jchat.domain.GetUsernameUseCase
import com.jcasben.jchat.domain.SaveUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val saveUsernameUseCase: SaveUsernameUseCase,
    private val getUsernameUseCase: GetUsernameUseCase
) : ViewModel() {

    init {
        verifyUserLogged()
    }

    private var _uiState = MutableStateFlow<HomeViewState>(HomeViewState.LOADING)
    val uiState: StateFlow<HomeViewState> = _uiState

    fun saveUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUsernameUseCase(username)
        }
    }

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val username = async { getUsernameUseCase() }.await()
            if (username.isNotEmpty()) {
                _uiState.value = HomeViewState.REGISTERED
            } else {
                _uiState.value = HomeViewState.UNREGISTERED
            }
        }
    }
}

sealed class HomeViewState {
    object UNREGISTERED: HomeViewState()
    object REGISTERED: HomeViewState()
    object LOADING: HomeViewState()
}