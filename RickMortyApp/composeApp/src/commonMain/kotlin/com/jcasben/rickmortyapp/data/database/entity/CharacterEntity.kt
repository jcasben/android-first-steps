package com.jcasben.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jcasben.rickmortyapp.domain.model.CharacterModel
import com.jcasben.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "characteroftheday")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDate: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
) {
    fun toDomain(): CharacterOfTheDayModel? {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                isAlive = isAlive,
                image = image,
                name = name,
                species = species,
                gender = gender,
                origin = origin,
                episodes = Json.decodeFromString<List<String>>(episodes)
            ),
            selectedDate = selectedDate
        )
    }
}
