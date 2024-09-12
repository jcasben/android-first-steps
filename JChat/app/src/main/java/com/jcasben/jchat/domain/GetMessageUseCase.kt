package com.jcasben.jchat.domain

import com.jcasben.jchat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke() = firebaseChatService.getMessages()
}