package com.jcasben.todoapp.categories

sealed class TaskCategory {
    object Personal: TaskCategory()
    object Uni: TaskCategory()
    object Other: TaskCategory()
}