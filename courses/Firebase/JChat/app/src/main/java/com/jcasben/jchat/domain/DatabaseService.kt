package com.jcasben.jchat.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    suspend fun saveUsername(username: String)

    fun getUsername(): Flow<String>

    suspend fun deleteUsername()
}