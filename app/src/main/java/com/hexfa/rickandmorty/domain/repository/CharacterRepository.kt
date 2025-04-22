package com.hexfa.rickandmorty.domain.repository

import com.hexfa.rickandmorty.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}