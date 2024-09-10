package com.jcasben.jchat.data.network

import com.google.firebase.database.DatabaseReference
import com.jcasben.jchat.data.network.dto.MessageDto
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMessage(message: MessageDto) {
        val newMessage = reference.child(PATH).push()
        newMessage.setValue(message)
    }
}