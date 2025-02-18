package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.datasource.RemoteCharacterDataSource
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val remoteDataSource: RemoteCharacterDataSource
) : CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
        return remoteDataSource.getCharacters()
    }
}