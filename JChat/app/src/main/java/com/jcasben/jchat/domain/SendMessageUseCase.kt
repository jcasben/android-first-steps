package com.jcasben.jchat.domain

import com.jcasben.jchat.data.network.FirebaseChatService
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {
    operator fun invoke(message: String) {
        firebaseChatService.sendMessage(message)
    }
}