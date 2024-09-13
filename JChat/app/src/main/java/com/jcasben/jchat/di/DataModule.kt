package com.jcasben.jchat.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.jcasben.jchat.data.database.DatabaseServiceImpl
import com.jcasben.jchat.data.network.FirebaseChatService
import com.jcasben.jchat.domain.DatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideFirebaseService(reference: DatabaseReference) = FirebaseChatService(reference)

    @Singleton
    @Provides
    fun provideDatabaseReference() =
        Firebase.database("https://jchat-8e56c-default-rtdb.europe-west1.firebasedatabase.app/").reference

    @Singleton
    @Provides
    fun provideDataStoreService(@ApplicationContext context: Context): DatabaseService =
        DatabaseServiceImpl(context)
}