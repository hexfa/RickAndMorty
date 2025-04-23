package com.hexfa.rickandmorty.data.mapper

import com.plcoding.CharactersQuery
import org.junit.Assert
import org.junit.Test

class CharacterMapperTest {

    @Test
    fun `test toCharacter should return valid Character when all fields are present`() {
        // Arrange
        val result = CharactersQuery.Result(
            name = "Rick Sanchez",
            image = "rick_image_url",
            id = "1",
            gender = "Male",
            species = "Human"
        )

        // Act
        val character = result.toCharacter()

        // Assert
        Assert.assertEquals("Rick Sanchez", character.name)
        Assert.assertEquals("rick_image_url", character.image)
        Assert.assertEquals("1", character.id)
        Assert.assertEquals("Male", character.gender)
        Assert.assertEquals("Human", character.species)
    }

    @Test
    fun `test toCharacter should return default values for null fields`() {
        // Arrange
        val result = CharactersQuery.Result(
            name = null,
            image = null,
            id = null,
            gender = null,
            species = null
        )

        // Act
        val character = result.toCharacter()

        // Assert
        Assert.assertEquals("Name Error", character.name)
        Assert.assertEquals("", character.image)
        Assert.assertEquals("", character.id)
        Assert.assertEquals("", character.gender)
        Assert.assertEquals("", character.species)
    }

    @Test
    fun `test toCharacter should return default value for missing fields`() {
        // Arrange
        val result = CharactersQuery.Result(
            name = "Morty Smith",
            image = null,
            id = "2",
            gender = null,
            species = "Human"
        )

        // Act
        val character = result.toCharacter()

        // Assert
        Assert.assertEquals("Morty Smith", character.name)
        Assert.assertEquals("", character.image) // Default value when image is null
        Assert.assertEquals("2", character.id)
        Assert.assertEquals("", character.gender) // Default value when gender is null
        Assert.assertEquals("Human", character.species)
    }
}
