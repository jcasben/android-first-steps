package com.jcasben.fbaseauth.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jcasben.fbaseauth.databinding.ActivityLoginBinding
import com.jcasben.fbaseauth.ui.detail.DetailActivity
import com.jcasben.fbaseauth.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.isLoading.collect {
                    binding.pbLoading.isVisible = it
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.login(
                email = binding.tietUser.text.toString(),
                password = binding.tietPassword.text.toString(),
                navigateToDetail = { navigateToDetail() }
            )
        }
        binding.Register.setOnClickListener { navigateToSignUp() }
    }

    private fun navigateToSignUp() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun navigateToDetail() {
        startActivity(Intent(this, DetailActivity::class.java))
    }
}