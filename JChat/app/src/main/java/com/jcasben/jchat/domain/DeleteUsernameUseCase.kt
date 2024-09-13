package com.jcasben.jchat.domain

import javax.inject.Inject

class DeleteUsernameUseCase @Inject constructor(private val databaseService: DatabaseService) {
    suspend operator fun invoke() {
        databaseService.deleteUsername()
    }
}