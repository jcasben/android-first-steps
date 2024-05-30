package com.jcasben.todoapp.tasks

import com.jcasben.todoapp.categories.TaskCategory

data class Task(val name: String, val category: TaskCategory, var isSelected: Boolean = false)