package com.jcasben.jchat.domain.model

data class MessageModel(
    val message: String,
    val hour: String,
    val date: String,
    val user: UserModel
)

data class UserModel(
    val username: String,
    val admin: Boolean
)
