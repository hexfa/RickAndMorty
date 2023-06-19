package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.SimpleCharacter
import com.plcoding.CharactersQuery

class ApolloCharactersClient(
    private val apolloClient: ApolloClient
): CharacterClient {

    override suspend fun getCharacters(): List<SimpleCharacter> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results
            ?.map { it -> it!!.simpleCharacter() }
            ?: emptyList()
    }


}