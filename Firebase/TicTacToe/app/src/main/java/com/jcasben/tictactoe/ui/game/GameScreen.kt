package com.jcasben.tictactoe.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
    Board(game)
}

@Composable
private fun Board(game: GameModel?) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Game Id: ${game?.gameId.orEmpty()}")
        val status = if(game?.isGameReady == true) {
            "Your turn/Your opponent's turn"
        } else {
            "Waiting for your opponent"
        }

        Text(text = status)
        Row {
            GameCell()
            GameCell()
            GameCell()
        }
        Row {
            GameCell()
            GameCell()
            GameCell()
        }
        Row {
            GameCell()
            GameCell()
            GameCell()
        }
    }
}

@Composable
private fun GameCell() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .border(BorderStroke(2.dp, Color.Black)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "X")
    }
}