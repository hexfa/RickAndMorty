package com.hexfa.rickandmorty.data.repository

import com.hexfa.rickandmorty.data.datasource.remote.RemoteCharacterDataSource
import com.hexfa.rickandmorty.domain.model.Character
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class CharacterRepositoryImpTest {

    private lateinit var remoteDataSource: RemoteCharacterDataSource
    private lateinit var repository: CharacterRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mock(RemoteCharacterDataSource::class.java)
        repository = CharacterRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getCharacters returns expected list from remoteDataSource`() = runTest {
        // Given
        val expectedCharacters = listOf(
            Character(
                name = "Abradolf Lincler",
                image = "",
                id = "2",
                gender = "Male",
                species = "Human"
            ),
            Character(
                name = "Abadango Cluster Princess",
                image = "",
                id = "1",
                gender = "Female",
                species = "Alien"
            ),
        )
        `when`(remoteDataSource.getCharacters()).thenReturn(expectedCharacters)

        // When
        val result = repository.getCharacters()

        // Then
        assertEquals(expectedCharacters, result)
        verify(remoteDataSource).getCharacters()
    }
}