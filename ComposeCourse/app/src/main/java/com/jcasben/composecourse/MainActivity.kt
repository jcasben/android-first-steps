package com.jcasben.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.jcasben.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyState()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyState() {
    var counter by rememberSaveable { mutableIntStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1 }) {
            Text(text = "Click")
        }
        Text(text = "I have been clicked $counter times!")
    }
}

@Composable
fun ConstraintGuide() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val topGuide = createGuidelineFromTop(0.1f)
        val startGuide = createGuidelineFromStart(0.25f)
        val redBox = createRef()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (greenBox, redBox, yellowBox) = createRefs()
        val barrier = createEndBarrier(redBox, greenBox)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(parent.start, margin = 16.dp)
            })
        Box(modifier = Modifier
            .size(225.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                top.linkTo(redBox.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(barrier)
            })
    }
}

@Composable
fun ConstraintChain() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (greenBox, redBox, yellowBox) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(parent.start)
                end.linkTo(greenBox.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                start.linkTo(redBox.end)
                end.linkTo(yellowBox.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(greenBox.end)
                end.linkTo(parent.end)
            })

        createHorizontalChain(redBox, greenBox, yellowBox, chainStyle = ChainStyle.SpreadInside)
    }
}

@Composable
fun MyConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxGreen, boxMagenta, boxYellow, boxBlue) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                bottom.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                end.linkTo(boxRed.start)
            })
    }
}

@Composable
fun Exercise1() {
    Column(
        Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text("Text 1")
        }
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text("Text 2")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Text("Text 3")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Magenta),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Text 4")
        }
    }
}

@Composable
fun MyComplexLayout() {
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green)
        ) {}
        Spacer(
            modifier = Modifier
                .width(0.dp)
                .height(30.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello World")
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Blue)
            ) {}
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan)
        ) {}
    }
}

@Composable
fun MyRow() {
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ejemplo 1")
        Text(text = "Ejemplo 2")
        Text(text = "Ejemplo 3")
    }
}

@Composable
fun MyColumn() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Hola 1", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = "Hola 2",
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = "Hola 3",
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(Color.Cyan)
                .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
        ) {
            Text("TEXTO DE EJEMPLO")
        }
    }
}