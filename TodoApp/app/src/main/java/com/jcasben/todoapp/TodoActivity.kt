package com.jcasben.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jcasben.todoapp.categories.CategoriesAdapter
import com.jcasben.todoapp.categories.TaskCategory
import com.jcasben.todoapp.tasks.Task
import com.jcasben.todoapp.tasks.TasksAdapter

class TodoActivity : AppCompatActivity() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fabAddTask: FloatingActionButton

    private val categories = listOf(
        TaskCategory.Personal,
        TaskCategory.Uni,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("Test coding", TaskCategory.Personal),
        Task("Test Other", TaskCategory.Other),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
        Task("Test Uni", TaskCategory.Uni),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString();
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_uni_category) -> TaskCategory.Uni
                    getString(R.string.todo_dialog_personal_category) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }
                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()
            }
        }

        dialog.show()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateTasks() {
        tasksAdapter.notifyDataSetChanged()
    }
}