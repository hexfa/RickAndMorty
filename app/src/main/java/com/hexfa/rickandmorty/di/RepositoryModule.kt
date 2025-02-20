package com.hexfa.rickandmorty.di

import com.hexfa.rickandmorty.data.datasource.RemoteCharacterDataSource
import com.hexfa.rickandmorty.data.repository.CharacterRepositoryImpl
import com.hexfa.rickandmorty.domain.repository.CharacterRepository
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