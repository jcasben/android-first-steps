package com.jcasben.rickmortyapp.ui.home.tabs.episodes

import androidx.paging.PagingData
import com.jcasben.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class EpisodesState(
    val characters: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)