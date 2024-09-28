package com.jcasben.rickmortyapp.domain

import com.jcasben.rickmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
}