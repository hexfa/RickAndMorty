package com.hexfa.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.hexfa.rickandmorty.presentation.view.character.CharactersScreen
import com.hexfa.rickandmorty.presentation.base.ScreenBase
import com.hexfa.rickandmorty.presentation.view.setting.SettingViewModel
import com.hexfa.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()
            val themeState by settingViewModel.isDarkTheme.collectAsState()
            val isDarkTheme = remember { mutableStateOf(themeState) }

            LaunchedEffect(themeState) {
                delay(150)
                isDarkTheme.value = themeState
            }

            Crossfade(targetState = isDarkTheme.value, label = "Theme Crossfade") { darkTheme ->
                RickAndMortyTheme(darkTheme = darkTheme) {
                    ScreenBase {
                        CharactersScreen(settingViewModel)
                    }
                }
            }
        }
    }
}

