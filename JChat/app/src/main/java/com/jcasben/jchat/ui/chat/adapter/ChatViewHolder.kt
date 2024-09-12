package com.jcasben.jchat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jcasben.jchat.databinding.ItemChatMeBinding
import com.jcasben.jchat.databinding.ItemChatOtherBinding
import com.jcasben.jchat.domain.model.MessageModel

class ChatViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when(itemViewType) {
            ChatAdapter.SENT_MESSAGE -> { bindSentMessage(messageModel) }
            ChatAdapter.RECEIVED_MESSAGE -> { bindReceivedMessage(messageModel) }
        }
    }

    private fun bindReceivedMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOtherBinding
        currentBinding.apply {
            tvDateMe.text = messageModel.date
            messageBody.text = messageModel.message
            messageOwner.text = messageModel.user.username
            tvHour.text = messageModel.hour
        }
    }

    private fun bindSentMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        currentBinding.apply {
            tvMessageBody.text = messageModel.message
            tvDateMe.text = messageModel.date
            tvHour.text = messageModel.hour
        }
    }
}