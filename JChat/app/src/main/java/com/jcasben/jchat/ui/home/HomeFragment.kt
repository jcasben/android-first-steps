package com.jcasben.jchat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jcasben.jchat.R
import com.jcasben.jchat.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.btnChat.setOnClickListener {
            if (!binding.tietName.text.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_navigate_from_home_to_chat)
            }
        }
        return binding.root
    }
}