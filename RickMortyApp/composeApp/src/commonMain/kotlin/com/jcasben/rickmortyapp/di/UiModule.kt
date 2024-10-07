package com.jcasben.rickmortyapp.di

import com.jcasben.rickmortyapp.ui.detail.CharacterDetailViewModel
import com.jcasben.rickmortyapp.ui.home.tabs.characters.CharactersViewModel
import com.jcasben.rickmortyapp.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}