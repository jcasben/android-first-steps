package com.jcasben.rickmortyapp.ui.home.tabs.episodes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.jcasben.rickmortyapp.domain.model.EpisodeModel
import com.jcasben.rickmortyapp.domain.model.SeasonEpisode
import com.jcasben.rickmortyapp.ui.core.components.LoadingIndicator
import com.jcasben.rickmortyapp.ui.core.components.PagingType
import com.jcasben.rickmortyapp.ui.core.components.PagingWrapper
import com.jcasben.rickmortyapp.ui.core.components.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.portal
import rickmortyapp.composeapp.generated.resources.season1
import rickmortyapp.composeapp.generated.resources.season2
import rickmortyapp.composeapp.generated.resources.season3
import rickmortyapp.composeapp.generated.resources.season4
import rickmortyapp.composeapp.generated.resources.season5
import rickmortyapp.composeapp.generated.resources.season6
import rickmortyapp.composeapp.generated.resources.season7

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {
    val episodesViewModel = koinViewModel<EpisodesViewModel>()
    val state by episodesViewModel.state.collectAsState()
    val episodes = state.characters.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        PagingWrapper(
            modifier = Modifier.padding(vertical = 8.dp),
            pagingType = PagingType.Row(horizontalArrangement = Arrangement.spacedBy(8.dp)),
            pagingItems = episodes,
            initialView = { LoadingIndicator() },
            extraItemsView = { LoadingIndicator() },
        ) {
            EpisodeItemList(it) { episodesViewModel.onPlaySelected(it) }
        }

        EpisodePlayer(state.playVideo) { episodesViewModel.onCloseVideo() }
    }
}

@Composable
fun EpisodeItemList(episode: EpisodeModel, onEpisodeSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .clickable { onEpisodeSelected(episode.videoURL) }
    ) {
        Image(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            contentDescription = "episode image",
            contentScale = ContentScale.Inside,
            painter = painterResource(getSeasonImage(episode.season))
        )
    }
}

fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource {
    return when (seasonEpisode) {
        SeasonEpisode.SEASON_1 -> Res.drawable.season1
        SeasonEpisode.SEASON_2 -> Res.drawable.season2
        SeasonEpisode.SEASON_3 -> Res.drawable.season3
        SeasonEpisode.SEASON_4 -> Res.drawable.season4
        SeasonEpisode.SEASON_5 -> Res.drawable.season5
        SeasonEpisode.SEASON_6 -> Res.drawable.season6
        SeasonEpisode.SEASON_7 -> Res.drawable.season7
        SeasonEpisode.UNKNOWN -> Res.drawable.season1
    }
}

@Composable
fun EpisodePlayer(playVideo: String, onCloseVideo: () -> Unit) {
    AnimatedVisibility (playVideo.isNotBlank()) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().height(250.dp).padding(16.dp)
                .border(3.dp, Color.Green, CardDefaults.elevatedShape)
        ) {
            Box(modifier = Modifier.background(Color.Black)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(16.dp)) {
                    VideoPlayer(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        playVideo
                    )
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(Res.drawable.portal),
                        contentDescription = "close video button",
                        modifier = Modifier.size(40.dp).padding(8.dp).clickable { onCloseVideo() }
                    )
                }
            }
        }
    }
}
