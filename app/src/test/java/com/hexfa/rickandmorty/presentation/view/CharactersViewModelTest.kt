package com.hexfa.rickandmorty.presentation.view

import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.domain.usecase.GetCharactersUseCase
import com.hexfa.rickandmorty.presentation.view.character.CharactersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest {

    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var viewModel: CharactersViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        getCharactersUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `character list is loaded from use case`() = runTest {
        val characters = listOf(
            Character(
                name = "Abadango Cluster Princess",
                image = "",
                id = "1",
                gender = "Male",
                species = "Human"
            ),
            Character(
                name = "Abradolf Lincler",
                image = "",
                id = "2",
                gender = "Male",
                species = "Human"
            ),
        )

        coEvery { getCharactersUseCase.execute() } returns characters

        viewModel = CharactersViewModel(getCharactersUseCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(2, state.characterList.size)
        assertEquals("Abadango Cluster Princess", state.characterList[0].name)
    }
}