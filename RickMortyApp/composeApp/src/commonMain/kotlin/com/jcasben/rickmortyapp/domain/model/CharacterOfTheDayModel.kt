package com.jcasben.rickmortyapp.domain.model

import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val selectedDate: String
) {
    fun toEntity(): CharacterEntity {
        return CharacterEntity(
            id = characterModel.id,
            isAlive = characterModel.isAlive,
            image = characterModel.image,
            name = characterModel.name,
            selectedDate = selectedDate,
            species = characterModel.species,
            gender = characterModel.gender,
            origin = characterModel.origin,
            episodes = Json.encodeToString(characterModel.episodes)
        )
    }
}
