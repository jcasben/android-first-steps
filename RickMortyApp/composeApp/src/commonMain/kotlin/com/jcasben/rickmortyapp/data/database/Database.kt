package com.jcasben.rickmortyapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.jcasben.rickmortyapp.data.database.dao.UserPreferenceDao
import com.jcasben.rickmortyapp.data.database.entity.CharacterEntity

const val DATABASE_NAME = "rm_app_database.db"

expect object RickMortyCTor : RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterEntity::class], version = 1)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun getPreferencesDao(): UserPreferenceDao
}