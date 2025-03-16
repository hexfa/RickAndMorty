package com.hexfa.rickandmorty.data.datasource.remote

import com.apollographql.apollo3.ApolloClient
import com.hexfa.rickandmorty.data.mapper.toCharacter
import com.hexfa.rickandmorty.domain.model.Character
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
