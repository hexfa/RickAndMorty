package com.hexfa.rickandmorty.domain.usecase

import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun execute(): List<Character> {
        return characterRepository
            .getCharacters()
            .sortedBy { it.name }
    }
}