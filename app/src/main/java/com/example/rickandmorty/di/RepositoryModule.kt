package com.example.rickandmorty.di

import com.example.rickandmorty.data.datasource.RemoteCharacterDataSource
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.repository.CharacterRepository
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
    fun provideCharacterRepository(remoteDataSource: RemoteCharacterDataSource): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }
}