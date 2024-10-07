package com.jcasben.tictactoe.ui.game

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcasben.tictactoe.ui.model.GameModel
import com.jcasben.tictactoe.ui.model.PlayerType
import com.jcasben.tictactoe.ui.theme.Accent
import com.jcasben.tictactoe.ui.theme.Background
import com.jcasben.tictactoe.ui.theme.BlueLink
import com.jcasben.tictactoe.ui.theme.OrangeMain
import com.jcasben.tictactoe.ui.theme.OrangeSecondary

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean,
    navigateHome: () -> Unit
) {
    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }

    val game: GameModel? by gameViewModel.game.collectAsState()
    val winner: PlayerType? by gameViewModel.winner.collectAsState()

    if (winner != null && winner != PlayerType.Empty) WinnerScreen(winner!!, navigateHome)
    else if (winner == PlayerType.Empty) DrawScreen(navigateHome)
    else Board(game) { gameViewModel.onItemSelected(it) }
}

@Composable
private fun Board(game: GameModel?, onItemSelected: (Int) -> Unit) {
    if (game == null) return

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Game Id: ",
                color = Accent
            )
            Text(
                text = game.gameId,
                color = BlueLink,
                modifier = Modifier
                    .clickable {
                        clipboardManager.setText(AnnotatedString(game.gameId))
                        Toast
                            .makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }

        val status = if (game.isGameReady) {
            if (game.isMyTurn) {
                "Your Turn"
            } else {
                "Your opponent's turn"
            }
        } else {
            "Waiting for your opponent"
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = status, fontSize = 24.sp, color = Accent, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(6.dp))
            if (!game.isGameReady || !game.isMyTurn) {
                CircularProgressIndicator(
                    Modifier.size(18.dp),
                    color = OrangeMain,
                    backgroundColor = OrangeSecondary,
                )
            }
        }

        Spacer(Modifier.height(24.dp))

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
            .size(80.dp)
            .padding(8.dp)
            .border(BorderStroke(2.dp, Accent))
            .clickable { onItemSelected() },
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(targetState = playerType.symbol, label = "") {
            Text(
                text = it,
                color = if (playerType is PlayerType.FirstPlayer) OrangeMain else OrangeSecondary,
                fontSize = 28.sp
            )
        }
    }
}

@Composable
fun WinnerScreen(winner: PlayerType, navigateHome: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        val currentWinner = if (winner == PlayerType.FirstPlayer) "Player 1" else "Player 2"
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(24.dp),
            backgroundColor = Background,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, OrangeMain)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("CONGRATS!!", fontSize = 28.sp, color = OrangeMain)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$currentWinner won!", color = Accent, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { navigateHome() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = OrangeMain)
                ) {
                    Text(text = "Return Home", color = Accent)
                }
            }
        }
    }
}

@Composable
private fun DrawScreen(navigateHome: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(24.dp),
            backgroundColor = Background,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, OrangeMain)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("It's a draw!", fontSize = 28.sp, color = OrangeMain)
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { navigateHome() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = OrangeMain)
                ) {
                    Text(text = "Return Home", color = Accent)
                }
            }
        }
    }
}