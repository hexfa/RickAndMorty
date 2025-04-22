package com.hexfa.rickandmorty.data.mapper

import com.hexfa.rickandmorty.domain.model.Character
import com.plcoding.CharactersQuery

fun CharactersQuery.Result.toCharacter(): Character {
    return Character(
        name = name ?: "Name Error",
        image = image ?: "",
        id = id ?: "",
        gender = gender ?: "",
        species = species ?: ""
    )
}
