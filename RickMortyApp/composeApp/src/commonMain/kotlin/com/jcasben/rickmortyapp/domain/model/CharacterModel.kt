package com.jcasben.rickmortyapp.domain.model

data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String
)
