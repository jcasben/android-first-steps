package com.jcasben.basicrealtime.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class FirebaseInstance(context: Context) {

    private val database =
        Firebase.database("https://basicrealtime-4100d-default-rtdb.europe-west1.firebasedatabase.app/")
    private val ref = database.reference


    init {
        FirebaseApp.initializeApp(context)
    }

    fun writeOnFirebase() {
        val newItem = ref.push()
        val random = Random.nextInt(1, 999).toString()
//        ref.setValue("Random write: $random")
        newItem.setValue(getGenericTodoItem(random))
    }

    private fun getGenericTodoItem(random: String) = TodoItem("tarea $random", "generic description")

    fun setupDatabaseListener(postListener: ValueEventListener) {
        database.reference.addValueEventListener(postListener)

    }

    fun removeFromDatabase(reference: String) {
        ref.child(reference).removeValue()
    }
}