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
}
