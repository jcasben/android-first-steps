package com.jcasben.tictactoe.ui.home

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcasben.tictactoe.R
import com.jcasben.tictactoe.ui.theme.Accent
import com.jcasben.tictactoe.ui.theme.Background
import com.jcasben.tictactoe.ui.theme.OrangeMain
import com.jcasben.tictactoe.ui.theme.OrangeSecondary

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {
    val context = LocalContext.current
    val toastMessage by homeViewModel.toastMessage.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        val gameExists by homeViewModel.gameExists.collectAsState()

        Header()
        Body(
            onCreateGame = { homeViewModel.onCreateGame(navigateToGame) },
            onJoinGame = { gameId -> homeViewModel.onJoinGame(gameId, navigateToGame) },
            gameExists
        )

        LaunchedEffect(toastMessage) {
            toastMessage?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                homeViewModel.clearToastMessage()
            }
        }
    }
}

@Composable
private fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, OrangeMain, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.applogo),
                contentDescription = "app logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            )
        }
        Row {
            Text(
                text = "Firebase ",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeMain
            )
            Text(
                text = "Tac Toe",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeSecondary
            )
        }
    }
}

@Composable
private fun Body(onCreateGame: () -> Unit, onJoinGame: (String) -> Unit, gameExists: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        backgroundColor = Background,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, OrangeMain)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            var createGame by remember { mutableStateOf(true) }

            Switch(
                checked = createGame,
                onCheckedChange = { createGame = it },
                colors = SwitchDefaults.colors(checkedThumbColor = OrangeSecondary)
            )

            AnimatedContent(targetState = createGame, label = "") {
                when (it) {
                    true -> CreateGame(onCreateGame)
                    false -> JoinGame(onJoinGame, gameExists)
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CreateGame(onCreateGame: () -> Unit) {
    Column {
        Button(
            onClick = { onCreateGame() },
            colors = ButtonDefaults.buttonColors(backgroundColor = OrangeMain)
        ) {
            Text(text = "Create New Game", color = Accent)
        }
    }
}

@Composable
private fun JoinGame(onJoinGame: (String) -> Unit, gameExists: Boolean) {
    var text by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Accent,
                focusedIndicatorColor = if (gameExists) OrangeMain else Color.Red,
                unfocusedIndicatorColor = if (gameExists) OrangeSecondary else Color.Red,
                cursorColor = OrangeMain
            )
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { onJoinGame(text) },
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(backgroundColor = OrangeMain)
        ) {
            Text(text = "Join Game", color = Accent)
        }
    }
}