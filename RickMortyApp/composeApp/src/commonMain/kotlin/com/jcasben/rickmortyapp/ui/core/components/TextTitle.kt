package com.jcasben.rickmortyapp.ui.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jcasben.rickmortyapp.ui.core.DefaultTextColor

@Composable
fun TextTitle(text: String) {
    Text(text = text.uppercase(), fontWeight = FontWeight.Bold, color = DefaultTextColor)
    Spacer(Modifier.height(8.dp))
}