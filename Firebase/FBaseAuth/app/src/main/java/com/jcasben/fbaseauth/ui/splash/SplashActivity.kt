package com.jcasben.fbaseauth.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jcasben.fbaseauth.R
import com.jcasben.fbaseauth.databinding.ActivitySplashBinding
import com.jcasben.fbaseauth.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToLogin()
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java)
        )
    }
}