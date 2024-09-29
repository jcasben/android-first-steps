package com.jcasben.firestorage.data

import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import javax.inject.Inject

class StorageService @Inject constructor(private val storage: FirebaseStorage) {

    fun basicExample() {
        val reference = Firebase.storage.reference.child("")
    }
}