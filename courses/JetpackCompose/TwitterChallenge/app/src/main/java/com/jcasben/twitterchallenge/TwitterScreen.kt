package com.jcasben.twitterchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwitterScreen() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProfilePhoto()
            Spacer(modifier = Modifier.size(12.dp))
            TwitContent()
        }
        HorizontalDivider(Modifier.fillMaxWidth().padding(top = 4.dp))
    }
}

@Composable
fun ProfilePhoto() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "profile picture",
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(60.dp)
    )
}

@Composable
fun TwitContent() {
    Column {
        Header()
        Content()
        Spacer(modifier = Modifier.size(8.dp))
        ButtonsBar()
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier.fillMaxWidth()) {
        CustomText(
            text = "Jesus",
            modifier = Modifier.padding(end = 8.dp),
            fontWeight = FontWeight.Bold
        )
        CustomText(text = "@jcasben", modifier = Modifier.padding(end = 8.dp), color = Color.Gray)
        CustomText(text = "4h", modifier = Modifier.padding(end = 8.dp), color = Color.Gray)
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.ic_dots), contentDescription = "three dots")
    }
}

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    Text(text = text, fontWeight = fontWeight, modifier = modifier, color = color)
}

@Composable
fun Content() {
    Column {
        CustomText(
            text = "Twit Content Twit Content Twit Content Twit Content Twit Content" +
                    "Twit Content Twit Content Twit Content Twit Content Twit Content Twit Content" +
                    "Twit Content Twit Content Twit Content Twit Content Twit Content Twit Content",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "attached photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.extraLarge),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ButtonsBar() {
    var comments by rememberSaveable { mutableStateOf(false) }
    var retwit by rememberSaveable { mutableStateOf(false) }
    var like by rememberSaveable { mutableStateOf(false) }
    Row {
        SocialIcon(
            modifier = Modifier.weight(1f),
            unselectedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "comments button"
                )
            },
            selectedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat_filled),
                    contentDescription = "comments button"
                )
            },
            isSelected = comments
        ) { comments = !comments }

        SocialIcon(
            modifier = Modifier.weight(1f),
            unselectedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rt),
                    contentDescription = "rt button"
                )
            },
            selectedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rt),
                    contentDescription = "rt button",
                    tint = Color.Green
                )
            },
            isSelected = retwit
        ) { retwit = !retwit }

        SocialIcon(
            modifier = Modifier.weight(1f),
            unselectedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = "like button"
                )
            },
            selectedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like_filled),
                    contentDescription = "like button",
                    tint = Color.Red
                )
            },
            isSelected = like
        ) { like = !like }
    }
}

@Composable
fun SocialIcon(
    modifier: Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val value = 1
    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) selectedIcon()
        else unselectedIcon()

        Text(
            text = if (isSelected) (value + 1).toString() else value.toString(),
            fontSize = 12.sp,
            modifier = modifier.padding(start = 4.dp)
        )
    }
}
