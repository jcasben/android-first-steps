package com.jcasben.basicrealtime

import androidx.recyclerview.widget.RecyclerView
import com.jcasben.basicrealtime.data.TodoItem
import com.jcasben.basicrealtime.databinding.ItemTodoBinding

class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(todoTask: Pair<String, TodoItem>, onItemSelected: (String) -> Unit) {
        binding.apply {
            tvTitle.text = todoTask.second.title
            tvDescription.text = todoTask.second.description
            tvReference.text = todoTask.first
            cvItem.setOnClickListener { onItemSelected(todoTask.first) }
        }
    }
}