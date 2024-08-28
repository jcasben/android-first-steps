package com.jcasben.basicrealtime.data

data class TodoItem(
    val title: String? = "",
    val description: String? = "",
    val isDone: Boolean? = false
)
