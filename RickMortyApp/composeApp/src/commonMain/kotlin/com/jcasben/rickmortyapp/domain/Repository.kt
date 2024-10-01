package com.jcasben.rickmortyapp.domain

import app.cash.paging.PagingData
import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity
import com.jcasben.rickmortyapp.domain.model.CharacterModel
import com.jcasben.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterEntity(): CharacterOfTheDayModel?
    suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel)
}