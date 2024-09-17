package com.jcasben.tictactoe.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcasben.tictactoe.ui.model.GameModel
import com.jcasben.tictactoe.ui.model.PlayerType

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {
    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }

    val game: GameModel? by gameViewModel.game.collectAsState()
    Board(game) { gameViewModel.onItemSelected(it) }
}

@Composable
private fun Board(game: GameModel?, onItemSelected: (Int) -> Unit) {
    if (game == null) return

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Game Id: ${game.gameId}")
        val status = if(game.isGameReady) {
            if (game.isMyTurn) {
                "Your Turn"
            } else {
                "Your opponent's turn"
            }
        } else {
            "Waiting for your opponent"
        }

        Text(text = status)
        Row {
            GameCell(game.board[0]) { onItemSelected(0) }
            GameCell(game.board[1]) { onItemSelected(1) }
            GameCell(game.board[2]) { onItemSelected(2) }
        }
        Row {
            GameCell(game.board[3]) { onItemSelected(3) }
            GameCell(game.board[4]) { onItemSelected(4) }
            GameCell(game.board[5]) { onItemSelected(5) }
        }
        Row {
            GameCell(game.board[6]) { onItemSelected(6) }
            GameCell(game.board[7]) { onItemSelected(7) }
            GameCell(game.board[8]) { onItemSelected(8) }
        }
    }
}

@Composable
private fun GameCell(playerType: PlayerType, onItemSelected: () -> Unit) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .border(BorderStroke(2.dp, Color.Black))
            .clickable { onItemSelected() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = playerType.symbol)
    }
}