package com.hexfa.rickandmorty.domain.usecase

import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    private lateinit var repository: CharacterRepository
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getCharactersUseCase = GetCharactersUseCase(repository)
    }

    @Test
    fun `execute should return sorted characters by name`() = runTest {
        // Arrange
        val characters = listOf(
            Character(id = "3", name = "Morty", image = "", gender = "", species = ""),
            Character(id = "1", name = "Beth", image = "", gender = "", species = ""),
            Character(id = "2", name = "Rick", image = "", gender = "", species = "")
        )
        coEvery { repository.getCharacters() } returns characters

        // Act
        val result = getCharactersUseCase.execute()

        // Assert
        val expected = characters.sortedBy { it.name }
        assertEquals(expected, result)
        coVerify(exactly = 1) { repository.getCharacters() }
    }
}