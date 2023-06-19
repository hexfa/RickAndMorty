package com.example.rickandmorty.data

import com.example.rickandmorty.domain.SimpleCharacter
import com.plcoding.CharactersQuery

fun CharactersQuery.Result.simpleCharacter():SimpleCharacter{
    return SimpleCharacter(name=name?:"Name Error",image=image?:"",id=id?:"",gender=gender?:"")
}
