package com.jcasben.jchat.data.network.response

import com.jcasben.jchat.domain.model.MessageModel
import com.jcasben.jchat.domain.model.UserModel

data class MessageResponse(
    val message: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
) {
    fun toDomain(): MessageModel {
        return MessageModel(
            message = message.orEmpty(),
            hour = hour.orEmpty(),
            date = date.orEmpty(),
            user = UserModel(username = user?.username ?: "Guest", admin = user?.admin ?: false)
        )
    }
}

data class UserResponse(
    val username: String? = null,
    val admin: Boolean? = null
)