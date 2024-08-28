package com.jcasben.firebaselite.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcasben.firebaselite.R
import com.jcasben.firebaselite.ui.theme.BackgroundButton
import com.jcasben.firebaselite.ui.theme.Black
import com.jcasben.firebaselite.ui.theme.Gray
import com.jcasben.firebaselite.ui.theme.Green
import com.jcasben.firebaselite.ui.theme.ShapeButton

@Composable
fun HomeScreen(navigateToLogin: () -> Unit = {}, navigateToSignUp: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Black), startY = 0f, endY = 500f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.spotify),
            contentDescription = "spotify icon",
            modifier = Modifier.clip(
                CircleShape
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Millons of songs.",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Free on Spotify",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navigateToSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Sign up free", color = Black)
        }
        Spacer(modifier = Modifier.size(8.dp))
        CustomButton(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.google),
            title = "Google"
        )
        Spacer(modifier = Modifier.size(8.dp))
        CustomButton(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.facebook),
            title = "Facebook"
        )
        Text(
            text = "Log In",
            color = Color.White,
            modifier = Modifier
                .padding(24.dp)
                .clickable { navigateToLogin() },
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CustomButton(painter: Painter, title: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(48.dp)
            .background(BackgroundButton)
            .border(2.dp, ShapeButton, shape = CircleShape),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            painter = painter,
            contentDescription = "google icon",
            Modifier
                .padding(start = 16.dp)
                .size(16.dp),
            tint = Color.Unspecified
        )
        Text(
            text = "Continue with $title",
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}