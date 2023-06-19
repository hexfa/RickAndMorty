package com.example.rickandmorty.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedCharacters
import com.example.rickandmorty.domain.GetCharactersUseCase
import com.example.rickandmorty.domain.SimpleCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {

            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                characterList = getCharactersUseCase.execute(),
                isLoading = false
            ) }
        }
    }



    fun dismissCharactersDialog() {
        _state.update { it.copy(
            selectedCharacters = null
        ) }
    }

    data class CharactersState(
        val characterList: List<SimpleCharacter> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCharacters: DetailedCharacters? = null
    )
}