package com.subhajeet.ebook.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideFirebaseDatabase():FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }
}