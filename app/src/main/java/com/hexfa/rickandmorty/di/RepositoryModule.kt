package com.hexfa.rickandmorty.di

import com.hexfa.rickandmorty.data.datasource.remote.RemoteCharacterDataSource
import com.hexfa.rickandmorty.data.datasource.local.SettingDataSource
import com.hexfa.rickandmorty.data.repository.CharacterRepositoryImpl
import com.hexfa.rickandmorty.data.repository.SettingRepositoryImp
import com.hexfa.rickandmorty.domain.repository.CharacterRepository
import com.hexfa.rickandmorty.domain.repository.SettingRepository
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

    @Provides
    @Singleton
    fun provideSettingRepository(settingDataSource: SettingDataSource): SettingRepository {
        return SettingRepositoryImp(settingDataSource)
    }
}