package com.example.rickandmorty.presentation

import com.example.rickandmorty.domain.model.DetailedCharacters
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.presentation.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModelBase() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        launchWithState {
            _state.update {
                it.copy(
                )
            }
            _state.update {
                it.copy(
                    characterList = getCharactersUseCase.execute(),
                )
            }
        }
    }


    fun dismissCharactersDialog() {
        _state.update {
            it.copy(
                selectedCharacters = null
            )
        }
    }

    data class CharactersState(
        val characterList: List<Character> = emptyList(),
        val selectedCharacters: DetailedCharacters? = null
    )
}