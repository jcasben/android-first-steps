package com.jcasben.rickmortyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity

@Dao
interface UserPreferenceDao {

    @Query("SELECT * FROM characteroftheday")
    suspend fun getCharacterOfTheDayDB(): CharacterEntity?

    @Insert(entity = CharacterEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(characterOfTheDay: CharacterEntity)
}