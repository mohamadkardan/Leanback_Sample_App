package com.example.leanbackpaging.di.module

import com.example.leanbackpaging.data.repository.AppRepositoryImpl
import com.example.leanbackpaging.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideAppRepository(appRepositoryImpl: AppRepositoryImpl) : AppRepository

}