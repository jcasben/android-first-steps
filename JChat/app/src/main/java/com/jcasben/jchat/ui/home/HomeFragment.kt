package com.jcasben.jchat.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jcasben.jchat.R
import com.jcasben.jchat.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.btnChat.setOnClickListener {
            if (!binding.tietName.text.isNullOrEmpty()) {
                viewModel.saveUsername(binding.tietName.text.toString())
                findNavController().navigate(R.id.action_navigate_from_home_to_chat)
            }
        }
        subscribeToState()
        return binding.root
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when(state) {
                    HomeViewState.LOADING -> { binding.pbLoading.isVisible = true }
                    HomeViewState.REGISTERED -> {
                        findNavController().navigate(R.id.action_navigate_from_home_to_chat)
                    }
                    HomeViewState.UNREGISTERED -> { binding.pbLoading.isVisible = false }
                }
            }
        }
    }
}