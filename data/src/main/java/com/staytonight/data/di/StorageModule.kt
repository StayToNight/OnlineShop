package com.staytonight.data.di

import android.content.Context
import android.content.SharedPreferences
import com.staytonight.data.storage.DataStorageImpl
import com.staytonight.domain.storage.DataStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    private const val SHARED_PREF_TAG = "SharedPrefTag"

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_TAG, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDataStorage(sharedPreferences: SharedPreferences): DataStorage =
        DataStorageImpl(sharedPreferences)

}