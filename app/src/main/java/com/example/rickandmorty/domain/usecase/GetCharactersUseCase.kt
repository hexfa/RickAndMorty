package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun execute(): List<Character> {
        return characterRepository
            .getCharacters()
            .sortedBy { it.name }
    }
}