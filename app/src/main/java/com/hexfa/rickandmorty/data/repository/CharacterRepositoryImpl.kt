package com.hexfa.rickandmorty.data.repository

import com.hexfa.rickandmorty.data.datasource.remote.RemoteCharacterDataSource
import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val remoteDataSource: RemoteCharacterDataSource
) : CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
        return remoteDataSource.getCharacters()
    }
}