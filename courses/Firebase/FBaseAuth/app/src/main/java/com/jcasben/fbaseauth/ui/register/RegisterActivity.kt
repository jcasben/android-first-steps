package com.jcasben.fbaseauth.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jcasben.fbaseauth.databinding.ActivityRegisterBinding
import com.jcasben.fbaseauth.ui.detail.DetailActivity
import com.jcasben.fbaseauth.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                registerViewModel.register(
                    email = binding.tietUser.text.toString(),
                    password = binding.tietPassword.text.toString()
                ) { navigateToDetail() }
            }
            tvLoginHere.setOnClickListener { navigateToLogin() }
        }
    }

    private fun navigateToDetail() {
        startActivity(Intent(this, DetailActivity::class.java))
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.isLoading.collect {
                    binding.pbLoading.isVisible = it
                }
            }
        }
    }
}