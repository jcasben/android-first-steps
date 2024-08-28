package com.jcasben.taskdo.addtasks.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.jcasben.taskdo.addtasks.ui.model.TaskModel

object ToDeleteTask {
    var toDelete: TaskModel = TaskModel(task = "")
}

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showAddDialog: Boolean by tasksViewModel.showAddDialog.observeAsState(initial = false)
    val showDeleteDialog: Boolean by tasksViewModel.showDeleteDialog.observeAsState(initial = false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<TaskUiState>(
        initialValue = TaskUiState.Loading,
        key1 = lifecycle,
        key2 = tasksViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            tasksViewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {
        is TaskUiState.Error -> {}
        TaskUiState.Loading -> {}
        is TaskUiState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                AddTaskDialog(
                    show = showAddDialog,
                    onDismiss = { tasksViewModel.onAddDialogClose() },
                    onTaskAdded = { tasksViewModel.onTaskCreated(it) },
                )
                DeleteTaskDialog(
                    show = showDeleteDialog,
                    onDismiss = { tasksViewModel.onDeleteDialogClose() },
                    onTaskDeleted = { tasksViewModel.onItemRemoved(it) }
                )
                FABAddTaskDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
                TasksList((uiState as TaskUiState.Success).tasks, tasksViewModel)
            }
        }
    }
}

@Composable
fun FABAddTaskDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = { tasksViewModel.onShowAddDialogClick() },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add task")
    }
}

@Composable
fun AddTaskDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onTaskAdded: (String) -> Unit,
) {
    var task by rememberSaveable { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(shape = MaterialTheme.shapes.medium) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Add a new task",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TextField(
                        value = task,
                        onValueChange = { task = it },
                        singleLine = true,
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = MaterialTheme.shapes.small
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = {
                        onTaskAdded(task)
                        task = ""
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Create Task")
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteTaskDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onTaskDeleted: (TaskModel) -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(shape = MaterialTheme.shapes.medium) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Are you sure that you want to delete this task?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = { onDismiss() }, modifier = Modifier.weight(1f)) {
                            Text(text = "No")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        TextButton(onClick = {
                            onTaskDeleted(ToDeleteTask.toDelete)
                        }, modifier = Modifier.weight(1f)) {
                            Text(text = "Yes")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TasksList(tasks: List<TaskModel>, tasksViewModel: TasksViewModel) {
    LazyColumn {
        item {
            Text(
                text = "TODO:",
                modifier = Modifier.padding(16.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        items(tasks, key = { it.id }) { task ->
            TaskItem(task, tasksViewModel)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    ToDeleteTask.toDelete = taskModel
                    tasksViewModel.onShowDeleteDialogClick()
                }),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(taskModel) })
        }
    }
}