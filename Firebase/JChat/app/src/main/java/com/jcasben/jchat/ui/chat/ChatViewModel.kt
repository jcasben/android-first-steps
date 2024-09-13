package com.jcasben.jchat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcasben.jchat.domain.DeleteUsernameUseCase
import com.jcasben.jchat.domain.GetMessageUseCase
import com.jcasben.jchat.domain.GetUsernameUseCase
import com.jcasben.jchat.domain.SendMessageUseCase
import com.jcasben.jchat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUsernameUseCase: GetUsernameUseCase,
    private val deleteUsernameUseCase: DeleteUsernameUseCase
) : ViewModel() {

    var username: String = ""

    init {
        getUsername()
        getMessages()
    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    fun sendMessage(message: String) {
        sendMessageUseCase(message, username)
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                _messageList.value = it
            }
        }
    }

    private fun getUsername() {
        viewModelScope.launch(Dispatchers.IO) { username = getUsernameUseCase() }
    }

    fun logout(onViewFinished: () -> Unit) {
        viewModelScope.launch {
            async { deleteUsernameUseCase() }.await()
            onViewFinished()
        }
    }
}