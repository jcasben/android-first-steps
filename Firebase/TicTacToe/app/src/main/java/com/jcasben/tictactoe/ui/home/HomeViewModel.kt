package com.jcasben.tictactoe.ui.home

import androidx.lifecycle.ViewModel
import com.jcasben.tictactoe.data.network.FirebaseService
import com.jcasben.tictactoe.data.network.model.GameDataModel
import com.jcasben.tictactoe.data.network.model.PlayerDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {

    fun onCreateGame(navigateToGame: (String, String, Boolean) -> Unit) {
        val game = createNewGame()
        val gameId = firebaseService.createGame(game)
        val userId = game.player1?.userId!!
        val owner = true
        navigateToGame(gameId, userId, owner)
    }

    fun onJoinGame(gameId: String, navigateToGame: (String, String, Boolean) -> Unit) {
        val owner = false
        navigateToGame(gameId, createUserId(), owner)
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
}