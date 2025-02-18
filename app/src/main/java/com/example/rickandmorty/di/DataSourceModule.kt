package com.example.rickandmorty.di

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.datasource.RemoteCharacterDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}