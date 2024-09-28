package com.jcasben.rickmortyapp.ui.home.tabs.characters

import com.jcasben.rickmortyapp.domain.model.CharacterModel

data class CharactersState(
    val characterOfTheDay: CharacterModel? = null
)