package com.jcasben.firestorage.ui.xml.upload

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jcasben.firestorage.databinding.ActivityUploadXmlactivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadXmlActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent = Intent(context, UploadXmlViewModel::class.java)
    }

    private lateinit var binding: ActivityUploadXmlactivityBinding
    private val uploadXmlViewModel: UploadXmlViewModel by viewModels()
    private lateinit var uri: Uri
    private var intentCameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadXmlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {

    }
}