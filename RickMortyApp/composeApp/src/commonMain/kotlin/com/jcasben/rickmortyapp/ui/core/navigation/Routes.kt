package com.jcasben.rickmortyapp.ui.core.navigation

import com.jcasben.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {
    data object Home: Routes("home")

    // Home bottom nav
    data object Episodes: Routes("episodes")
    data object Characters: Routes("characters")

}

@Serializable
data class CharacterDetail(val characterModel: String)