package com.jcasben.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.jcasben.rickmortyapp.domain.model.CharacterModel
import com.jcasben.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.jcasben.rickmortyapp.ui.core.DefaultTextColor
import com.jcasben.rickmortyapp.ui.core.Green
import com.jcasben.rickmortyapp.ui.core.components.LoadingIndicator
import com.jcasben.rickmortyapp.ui.core.components.PagingType
import com.jcasben.rickmortyapp.ui.core.components.PagingWrapper
import com.jcasben.rickmortyapp.ui.core.ext.vertical
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.rickface

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(navigateToDetail: (CharacterModel) -> Unit) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()

    PagingWrapper(
        modifier = Modifier.background(BackgroundPrimaryColor).padding(horizontal = 16.dp),
        pagingType = PagingType.VerticalGrid(
            cells = 2,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ),
        pagingItems = characters,
        initialView = { LoadingIndicator() },
        emptyView = { },
        header = {
            Column {
                Text(
                    text = "Characters",
                    color = DefaultTextColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                CharacterOfTheDay(state.characterOfTheDay)
            }
        },
        extraItemsView = { LoadingIndicator() },
    ) {
        CharacterItemList(it) { characterModel ->
            navigateToDetail(characterModel)
        }
    }
}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(
        modifier = Modifier.fillMaxWidth().height(400.dp).padding(bottom = 16.dp),
        shape = RoundedCornerShape(12)
    ) {
        if (characterModel == null) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Green)
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(
                            0f to Color.Black.copy(alpha = 0.9f),
                            0.4f to Color.White.copy(alpha = 0f)
                        )
                    )
                )
                Text(
                    text = characterModel.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight().vertical().rotate(-90f)
                )
            }
        }
    }
}

@Composable
fun CharacterItemList(character: CharacterModel, onCharacterSelected: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24))
            .border(
                2.dp,
                Green,
                shape = RoundedCornerShape(0, 24, 0, 24)
            )
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onCharacterSelected(character) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "character image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(Res.drawable.rickface)
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(60.dp).background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(alpha = 0f),
                        Color.Black.copy(alpha = 0.6f),
                        Color.Black.copy(alpha = 1f)
                    ),
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = character.name,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}