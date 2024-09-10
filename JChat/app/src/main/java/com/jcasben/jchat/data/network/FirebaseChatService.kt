package com.jcasben.jchat.data.network

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMessage(message: String) {
        val newMessage = reference.child(PATH).push()
        newMessage.setValue(message)
    }
}