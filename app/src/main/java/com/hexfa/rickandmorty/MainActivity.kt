package com.hexfa.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.hexfa.rickandmorty.presentation.CharactersScreen
import com.hexfa.rickandmorty.presentation.CharactersViewModel
import androidx.compose.runtime.getValue
import com.hexfa.rickandmorty.presentation.base.ScreenBase
import com.hexfa.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val viewModel = hiltViewModel<CharactersViewModel>()
                val state by viewModel.state.collectAsState()
                ScreenBase {
                    CharactersScreen(
                        state = state,
                        onDismissCharactersDialog = viewModel::dismissCharactersDialog
                    )
                }
            }
        }
    }
}

