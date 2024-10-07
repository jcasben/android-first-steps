package com.jcasben.firestorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jcasben.firestorage.databinding.ActivityMainBinding
import com.jcasben.firestorage.ui.compose.upload.UploadComposeActivity
import com.jcasben.firestorage.ui.xml.upload.UploadXmlActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnNavigateXML.setOnClickListener { startActivity(UploadXmlActivity.create(this)) }
        binding.btnNavigateCompose.setOnClickListener { startActivity(UploadComposeActivity.create(this)) }
    }
}