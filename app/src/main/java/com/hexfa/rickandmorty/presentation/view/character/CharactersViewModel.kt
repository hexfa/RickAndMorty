package com.hexfa.rickandmorty.presentation.view.character

import com.hexfa.rickandmorty.domain.usecase.GetCharactersUseCase
import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.presentation.base.ViewModelBase
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
            //todo mock data
            _state.update {
                it.copy(
                    characterList = getCharactersUseCase.execute(),
                )
            }
        }
    }


    data class CharactersState(
        val characterList: List<Character> = emptyList(),
    )
}