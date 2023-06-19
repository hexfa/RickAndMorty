package com.example.rickandmorty.domain

class GetCharactersUseCase(
    private val characterClient: CharacterClient
) {

    suspend fun execute(): List<SimpleCharacter> {

        return characterClient
            .getCharacters()
            .sortedBy { it.name }
    }
}