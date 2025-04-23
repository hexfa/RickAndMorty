package com.hexfa.rickandmorty.presentation.view.character

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

class CharacterItemGridTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacter = Character(
        id = "1",
        name = "Rick Sanchez",
        image = "",
        gender = "Male",
        species = "Human"
    )

    @Test
    fun characterItemGrid_displaysNameGenderSpecies() {
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharacterItemGrid(character = testCharacter)
            }
        }

        composeTestRule
            .onNodeWithText("Rick Sanchez")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Male")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Human")
            .assertIsDisplayed()
    }
}