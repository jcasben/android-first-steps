package com.jcasben.rickmortyapp.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import com.jcasben.rickmortyapp.data.database.RickMortyDatabase
import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity
import com.jcasben.rickmortyapp.data.remote.ApiService
import com.jcasben.rickmortyapp.data.remote.paging.CharactersPagingSource
import com.jcasben.rickmortyapp.domain.Repository
import com.jcasben.rickmortyapp.domain.model.CharacterModel
import com.jcasben.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val rickMortyDatabase: RickMortyDatabase
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { charactersPagingSource }).flow
    }

    override suspend fun getCharacterEntity(): CharacterOfTheDayModel? {
        return rickMortyDatabase.getPreferencesDao().getCharacterOfTheDayDB()?.toDomain()
    }

    override suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel) {
        rickMortyDatabase.getPreferencesDao().saveCharacter(characterOfTheDayModel.toEntity())
    }
}