package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}