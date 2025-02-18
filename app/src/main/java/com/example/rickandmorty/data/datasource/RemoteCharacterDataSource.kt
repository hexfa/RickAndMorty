package com.example.rickandmorty.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.mapper.toCharacter
import com.example.rickandmorty.domain.model.Character
import com.plcoding.CharactersQuery

class RemoteCharacterDataSource(
    private val apolloClient: ApolloClient
) {
    suspend fun getCharacters(): List<Character> {
        val response = apolloClient.query(CharactersQuery()).execute()
        val results = response.data?.characters?.results
        return results?.mapNotNull { it?.toCharacter() } ?: emptyList()
    }
}
