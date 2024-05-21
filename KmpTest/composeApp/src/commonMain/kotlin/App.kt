import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmptest.composeapp.generated.resources.Res
import kmptest.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen())
        {navigator -> SlideTransition(navigator)}
    }
}

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navigator.push(SecondScreen()) }) { Text("Ejemplo de navegacion") }
        }
    }
}

class SecondScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column (
            modifier = Modifier.fillMaxSize().background(Color.Magenta),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Segunda Pantalla", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navigator.pop() }) { Text("VOLVER") }
        }
    }
}