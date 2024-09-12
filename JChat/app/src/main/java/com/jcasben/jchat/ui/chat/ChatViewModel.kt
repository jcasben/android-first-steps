package com.jcasben.jchat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcasben.jchat.domain.GetMessageUseCase
import com.jcasben.jchat.domain.SendMessageUseCase
import com.jcasben.jchat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
) : ViewModel() {

    init {
        getMessages()
    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    fun sendMessage() {
        val message = "Hi!"
        sendMessageUseCase(message)
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                Log.i("jcasben", "messages: $it")
                _messageList.value = it
            }
        }
    }
}