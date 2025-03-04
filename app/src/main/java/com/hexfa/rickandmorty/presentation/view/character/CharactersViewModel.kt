package com.hexfa.rickandmorty.presentation.view.character

import com.hexfa.rickandmorty.domain.model.DetailedCharacters
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
//                    characterList = getCharactersUseCase.execute(),
                    characterList = listOf(
                        Character(
                            name = "Abadango Cluster Princess",
                            image = "",
                            id = "1",
                            gender = "Female",
                            species = "Alien"
                        ),
                        Character(
                            name = "Abradolf Lincler",
                            image = "",
                            id = "2",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Adjudicator Rick",
                            image = "",
                            id = "3",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Agency Director",
                            image = "",
                            id = "4",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Alan Rails",
                            image = "",
                            id = "5",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Albert Einstein",
                            image = "",
                            id = "6",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Alexander",
                            image = "",
                            id = "7",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Alien Googah",
                            image = "",
                            id = "8",
                            gender = "Unknown",
                            species = "Alien"
                        ),
                        Character(
                            name = "Alien Morty",
                            image = "",
                            id = "9",
                            gender = "Male",
                            species = "Alien"
                        ),
                        Character(
                            name = "Alien Rick",
                            image = "",
                            id = "10",
                            gender = "Male",
                            species = "Alien"
                        ),
                        Character(
                            name = "Amish Cyborg",
                            image = "",
                            id = "11",
                            gender = "Male",
                            species = "Alien"
                        ),
                        Character(
                            name = "Annie",
                            image = "",
                            id = "12",
                            gender = "Female",
                            species = "Human"
                        ),
                        Character(
                            name = "Antenna Morty",
                            image = "",
                            id = "13",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Antenna Rick",
                            image = "",
                            id = "14",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Ants in my Eyes Johnson",
                            image = "",
                            id = "15",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Beth Smith",
                            image = "",
                            id = "16",
                            gender = "Female",
                            species = "Human"
                        ),
                        Character(
                            name = "Jerry Smith",
                            image = "",
                            id = "17",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Morty Smith",
                            image = "",
                            id = "18",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Rick Sanchez",
                            image = "",
                            id = "19",
                            gender = "Male",
                            species = "Human"
                        ),
                        Character(
                            name = "Summer Smith",
                            image = "",
                            id = "20",
                            gender = "Female",
                            species = "Human"
                        ),
                    )
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