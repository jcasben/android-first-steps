package com.jcasben.rickmortyapp.domain

import com.jcasben.rickmortyapp.domain.model.CharacterModel
import com.jcasben.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(private val repository: Repository) {

    suspend operator fun invoke(): CharacterModel {
        val characterEntity = repository.getCharacterEntity()
        val selectedDate = getCurrentDayOfTheYear()
        return if (characterEntity != null && characterEntity.selectedDate == selectedDate) {
            characterEntity.characterModel
        } else {
            val result = getRandomCharacter()
            repository.saveCharacterDB(
                CharacterOfTheDayModel(
                    characterModel = result,
                    selectedDate = selectedDate
                )
            )
            result
        }
    }

    private suspend fun getRandomCharacter(): CharacterModel {
        val random: Int = (0..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localtime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localtime.dayOfYear}${localtime.year}"
    }
}