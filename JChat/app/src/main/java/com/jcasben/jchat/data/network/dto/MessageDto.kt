package com.jcasben.jchat.data.network.dto

data class MessageDto(
    val message: String,
    val hour: String,
    val date: String,
    val user: UserDto
)

data class UserDto(
    val username: String,
    val admin: Boolean
)