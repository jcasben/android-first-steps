package com.jcasben.taskdo.addtasks.domain

import com.jcasben.taskdo.addtasks.data.TaskRepository
import com.jcasben.taskdo.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.addTask(taskModel)
    }
}