package com.jcasben.jchat.ui.chat

import androidx.lifecycle.ViewModel
import com.jcasben.jchat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val sendMessageUseCase: SendMessageUseCase) : ViewModel() {

    fun sendMessage() {
        val message = "Hi!"
        sendMessageUseCase(message)
    }
}