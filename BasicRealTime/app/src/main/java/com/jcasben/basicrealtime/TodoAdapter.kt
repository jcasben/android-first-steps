package com.jcasben.basicrealtime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jcasben.basicrealtime.data.TodoItem
import com.jcasben.basicrealtime.databinding.ItemTodoBinding

class TodoAdapter(
    private var todoList: List<Pair<String, TodoItem>> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<TodoViewHolder>() {

    fun setNewList(newList: List<Pair<String, TodoItem>>) {
        todoList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position], onItemSelected)
    }
}