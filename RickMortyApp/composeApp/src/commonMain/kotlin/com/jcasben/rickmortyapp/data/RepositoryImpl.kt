package com.jcasben.rickmortyapp.data

import com.jcasben.rickmortyapp.data.remote.ApiService
import com.jcasben.rickmortyapp.domain.Repository
import com.jcasben.rickmortyapp.domain.model.CharacterModel

class RepositoryImpl(private val api: ApiService) : Repository {

    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }
}