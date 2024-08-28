package com.jcasben.taskdo.addtasks.ui

import com.jcasben.taskdo.addtasks.ui.model.TaskModel

sealed class TaskUiState {
    data object Loading: TaskUiState()
    data class Error(val throwable: Throwable): TaskUiState()
    data class Success(val tasks: List<TaskModel>): TaskUiState()
}