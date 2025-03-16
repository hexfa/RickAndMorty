package com.hexfa.rickandmorty.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.hexfa.rickandmorty.data.datasource.remote.RemoteCharacterDataSource
import com.hexfa.rickandmorty.data.datasource.local.SettingDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteCharacterDataSource(apolloClient: ApolloClient): RemoteCharacterDataSource {
        return RemoteCharacterDataSource(apolloClient)
    }

    @Provides
    @Singleton
    fun provideThemeDataSource(@ApplicationContext context: Context): SettingDataSource {
        return SettingDataSource(context)
    }
}