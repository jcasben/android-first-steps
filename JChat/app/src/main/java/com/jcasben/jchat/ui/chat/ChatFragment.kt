package com.jcasben.jchat.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcasben.jchat.R
import com.jcasben.jchat.databinding.FragmentChatBinding
import com.jcasben.jchat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        binding.apply {

            ivBack.setOnClickListener {
                viewModel.logout {
                    findNavController().navigate(R.id.action_back)
                }
            }
            btnSend.setOnClickListener {
                val message = binding.etMessage.text.toString()
                if (message.isNotEmpty()) {
                    viewModel.sendMessage(message)
                }
                binding.etMessage.text.clear()
            }
            ivLogout.setOnClickListener {
                viewModel.logout {
                    findNavController().navigate(R.id.action_back)
                }
            }
        }
        setUpUI()
        return binding.root
    }

    private fun setUpUI() {
        setUpMessages()
        subscribeToMessages()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        binding.tvToolbarTitle.text = String.format("Welcome, %s", viewModel.username)
    }

    private fun setUpMessages() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMessages.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessages() {
        lifecycleScope.launch {
            viewModel.messageList.collect {
                chatAdapter.updateList(it.toMutableList(), viewModel.username)
                setUpToolbar()
                binding.rvMessages.scrollToPosition(chatAdapter.itemCount - 1)
            }
        }
    }
}