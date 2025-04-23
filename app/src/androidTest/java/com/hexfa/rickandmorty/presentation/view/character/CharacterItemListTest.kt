package com.hexfa.rickandmorty.presentation.view.character

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

class CharacterItemListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacter = Character(
        id = "2",
        name = "Morty Smith",
        image = "",
        gender = "Male",
        species = "Human"
    )

    @Test
    fun characterItemList_displaysNameGenderSpecies() {
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharacterItemList(character = testCharacter)
            }
        }

        composeTestRule
            .onNodeWithText("Morty Smith")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Male")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Human")
            .assertIsDisplayed()
    }
}