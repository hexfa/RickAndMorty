package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.presentation.CharactersScreen
import com.example.rickandmorty.presentation.CharactersViewModel
import androidx.compose.runtime.getValue
import com.example.rickandmorty.presentation.base.ScreenBase
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
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

