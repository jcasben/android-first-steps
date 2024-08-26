package com.jcasben.taskdo.addtasks.domain

import com.jcasben.taskdo.addtasks.data.TaskRepository
import com.jcasben.taskdo.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}