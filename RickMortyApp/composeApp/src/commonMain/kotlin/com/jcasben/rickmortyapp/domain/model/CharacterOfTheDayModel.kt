package com.jcasben.rickmortyapp.domain.model

import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity

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
            selectedDate = selectedDate
        )
    }
}
