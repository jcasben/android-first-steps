package com.jcasben.rickmortyapp.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(modifier: Modifier = Modifier, url: String)