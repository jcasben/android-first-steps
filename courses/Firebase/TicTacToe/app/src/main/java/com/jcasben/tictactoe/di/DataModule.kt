package com.jcasben.tictactoe.di

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.jcasben.tictactoe.data.network.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesDatabaseReference() =
        Firebase.database("https://tictactoe-d3437-default-rtdb.europe-west1.firebasedatabase.app/").reference

    @Singleton
    @Provides
    fun providesFirebaseService(databaseReference: DatabaseReference) =
        FirebaseService(databaseReference)
}