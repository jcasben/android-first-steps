package com.jcasben.jchat.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jcasben.jchat.domain.DatabaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseServiceImpl @Inject constructor(private val context: Context) : DatabaseService {

    companion object {
        private val NAME = stringPreferencesKey("username")
    }

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore("user")

    override suspend fun saveUsername(username: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[NAME] = username
        }
    }

    override fun getUsername(): Flow<String> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[NAME] ?: ""
        }
    }

    override suspend fun deleteUsername() {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[NAME] = ""
        }
    }
}