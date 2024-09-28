package com.jcasben.rickmortyapp.domain

import com.jcasben.rickmortyapp.domain.model.CharacterModel

class GetRandomCharacterUseCase(private val repository: Repository) {

    suspend operator fun invoke(): CharacterModel {
        val random: Int = (0..826).random()
        return repository.getSingleCharacter(random.toString())
    }
}