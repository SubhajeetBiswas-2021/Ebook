package com.subhajeet.ebook.di

import com.google.firebase.database.FirebaseDatabase
import com.subhajeet.ebook.repo.Repo
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

    @Provides
    fun provideRepo(firebaseDatabase:FirebaseDatabase): Repo {
        return Repo(firebaseDatabase)
    }
}