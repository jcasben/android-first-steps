package com.jcasben.firestorage.ui.compose.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jcasben.firestorage.R
import com.jcasben.firestorage.databinding.ActivityUploadComposeBinding

class UploadComposeActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent = Intent(context, UploadComposeActivity::class.java)
    }

    private lateinit var binding: ActivityUploadComposeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadComposeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.composeView.setContent {
            UploadScreen()
        }
    }

    @Composable
    private fun UploadScreen() {

    }
}