package com.jcasben.firestorage.ui.xml.upload

import androidx.lifecycle.ViewModel
import com.jcasben.firestorage.data.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadXmlViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {

}