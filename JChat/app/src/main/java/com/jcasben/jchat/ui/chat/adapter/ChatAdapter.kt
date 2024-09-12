package com.jcasben.jchat.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jcasben.jchat.databinding.ItemChatMeBinding
import com.jcasben.jchat.databinding.ItemChatOtherBinding
import com.jcasben.jchat.domain.model.MessageModel

class ChatAdapter(
    private var messageList: MutableList<MessageModel>,
    private val username: String
) : RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = when (viewType) {
            SENT_MESSAGE -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            RECEIVED_MESSAGE -> {
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }

            else -> {
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position], getItemViewType(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].user.username == username) SENT_MESSAGE else RECEIVED_MESSAGE
    }

    fun updateList(messages: MutableList<MessageModel>) {
        messageList.clear()
        messageList.addAll(messages)
        notifyItemInserted(messageList.size - 1)
    }
}