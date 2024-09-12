package com.jcasben.jchat.data.network

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import com.jcasben.jchat.data.network.dto.MessageDto
import com.jcasben.jchat.data.network.response.MessageResponse
import com.jcasben.jchat.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMessage(message: MessageDto) {
        val newMessage = reference.child(PATH).push()
        newMessage.setValue(message)
    }

    fun getMessages(): Flow<List<MessageModel>> {
        return reference.child(PATH).snapshots.map { dataSnapshot ->
            dataSnapshot.children.mapNotNull {
                it.getValue(MessageResponse::class.java)?.toDomain()
            }
        }
    }
}