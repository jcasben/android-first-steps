package com.jcasben.taskdo.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcasben.taskdo.addtasks.domain.AddTaskUseCase
import com.jcasben.taskdo.addtasks.domain.DeleteTaskUseCase
import com.jcasben.taskdo.addtasks.domain.GetTasksUseCase
import com.jcasben.taskdo.addtasks.domain.UpdateTaskUseCase
import com.jcasben.taskdo.addtasks.ui.TaskUiState.Success
import com.jcasben.taskdo.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map(::Success)
        .catch { TaskUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TaskUiState.Loading
        )

    private val _showAddDialog = MutableLiveData<Boolean>()
    val showAddDialog: LiveData<Boolean> = _showAddDialog

    private val _showDeleteDialog = MutableLiveData<Boolean>()
    val showDeleteDialog: LiveData<Boolean> = _showDeleteDialog

    fun onAddDialogClose() {
        _showAddDialog.value = false
    }

    fun onDeleteDialogClose() {
        _showDeleteDialog.value = false
    }

    fun onTaskCreated(task: String) {
        val taskModel = TaskModel(task = task)
        viewModelScope.launch { addTaskUseCase(taskModel) }
        onAddDialogClose()
    }

    fun onShowAddDialogClick() {
        _showAddDialog.value = true
    }

    fun onShowDeleteDialogClick() {
        _showDeleteDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemoved(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
        onDeleteDialogClose()
    }
}