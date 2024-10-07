package com.jcasben.tictactoe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcasben.tictactoe.data.network.FirebaseService
import com.jcasben.tictactoe.data.network.model.GameDataModel
import com.jcasben.tictactoe.data.network.model.PlayerDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {

    private var _gameExists = MutableStateFlow<Boolean>(true)
    val gameExists: StateFlow<Boolean> = _gameExists

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    fun onCreateGame(navigateToGame: (String, String, Boolean) -> Unit) {
        val game = createNewGame()
        val gameId = firebaseService.createGame(game)
        val userId = game.player1?.userId!!
        val owner = true
        navigateToGame(gameId, userId, owner)
    }

    fun onJoinGame(gameId: String, navigateToGame: (String, String, Boolean) -> Unit) {
        val owner = false
        viewModelScope.launch {
            firebaseService.gameExists(gameId).take(1).collect { exists ->
                _gameExists.value = exists
                if (exists) navigateToGame(gameId, createUserId(), owner)
                else _toastMessage.value = "This game doesn't exist!"
            }
        }
    }

    private fun createNewGame(): GameDataModel {
        val currentPlayer = PlayerDataModel(playerType = 1)
        return GameDataModel(
            board = List(9) { 0 },
            player1 = currentPlayer,
            playerTurn = currentPlayer,
            player2 = null
        )
    }

    private fun createUserId(): String = UUID.randomUUID().toString()

    fun clearToastMessage() {
        _toastMessage.value = null
    }
}