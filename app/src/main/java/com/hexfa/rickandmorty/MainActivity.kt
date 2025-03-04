package com.hexfa.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hexfa.rickandmorty.presentation.view.character.CharactersScreen
import com.hexfa.rickandmorty.presentation.base.ScreenBase
import com.hexfa.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme(darkTheme = false) {
                ScreenBase {
                    CharactersScreen()
                }
            }
        }
    }
}

