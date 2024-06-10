package com.jcasben.todoapp.categories

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Personal: TaskCategory()
    object Uni: TaskCategory()
    object Other: TaskCategory()
}