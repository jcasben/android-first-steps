package com.jcasben.taskdo.addtasks.data

import com.jcasben.taskdo.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toData())
    }

    val tasks: Flow<List<TaskModel>> =
        taskDao.getTasks().map { items -> items.map { TaskModel(it.id, it.task, it.selected) } }

    suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toData())
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toData())
    }
}

fun TaskModel.toData(): TaskEntity = TaskEntity(this.id, this.task, this.selected)