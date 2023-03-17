package com.staytonight.data.di

import com.staytonight.data.network.Api
import com.staytonight.data.repository.AuthRepositoryImpl
import com.staytonight.data.repository.DetailsRepositoryImpl
import com.staytonight.data.repository.HomeRepositoryImpl
import com.staytonight.domain.repository.AuthRepository
import com.staytonight.domain.repository.DetailsRepository
import com.staytonight.domain.repository.HomeRepository
import com.staytonight.domain.storage.DataStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(dataStorage: DataStorage): AuthRepository =
        AuthRepositoryImpl(dataStorage)

    @Provides
    @Singleton
    fun provideHomeRepository(api: Api): HomeRepository =
        HomeRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDetailsRepository(api: Api): DetailsRepository =
        DetailsRepositoryImpl(api)
}