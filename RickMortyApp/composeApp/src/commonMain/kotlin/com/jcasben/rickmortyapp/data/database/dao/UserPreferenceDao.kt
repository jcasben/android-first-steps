package com.jcasben.rickmortyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity

@Dao
interface UserPreferenceDao {

    @Query("SELECT * FROM characteroftheday")
    suspend fun getCharacterOfTheDayDB(): CharacterEntity?
}