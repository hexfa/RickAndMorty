package com.hexfa.rickandmorty.presentation.view.character

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hexfa.rickandmorty.MainActivity
import com.hexfa.rickandmorty.presentation.view.setting.SettingViewModel
import com.hexfa.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CharacterScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var fakeCharactersViewModel: CharactersViewModel
    private lateinit var fakeSettingViewModel: SettingViewModel

    @Before
    fun setup() {
        hiltRule.inject()

        composeTestRule.setContent {
            RickAndMortyTheme {
                CharactersScreen(settingViewModel = fakeSettingViewModel)
            }
        }
    }

    @Test
    fun characters_are_displayed_in_list_by_default() {
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
    }

    @Test
    fun toggle_grid_view_changes_layout() {
        composeTestRule.onNodeWithContentDescription("More options").performClick()

        composeTestRule.onNodeWithText("Grid View").performClick()

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
    }
}
