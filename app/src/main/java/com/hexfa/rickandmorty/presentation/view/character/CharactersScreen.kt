package com.hexfa.rickandmorty.presentation.view.character

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.hexfa.rickandmorty.domain.model.DetailedCharacters
import com.hexfa.rickandmorty.presentation.view.setting.SettingViewModel

@Composable
fun CharactersScreen(settingViewModel: SettingViewModel) {

    val viewModel: CharactersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val expanded = remember { mutableStateOf(false) }
    val isGridView by settingViewModel.isGridView.collectAsState()
    val isDarkTheme by settingViewModel.isDarkTheme.collectAsState()
    val backgroundColor by animateColorAsState(
        targetValue = MaterialTheme.colors.background,
        label = "Background Color Animation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        TopAppBar(
            title = { Text("Characters") },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            actions = {
                IconButton(onClick = { expanded.value = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    ThemeIcon(expanded, settingViewModel, isDarkTheme)
                    GridIcon(expanded, settingViewModel, isGridView)
                }
            }
        )

        AnimatedContent(targetState = isGridView, label = "List-Grid Animation") { gridView ->
            if (gridView) {
                ProvideLazyVerticalGrid(state)
            } else {
                ProvideLazyColumn(state)
            }
        }

        if (state.selectedCharacters != null) {
            CountryDialog(
                characters = state.selectedCharacters!!,
                onDismiss = viewModel::dismissCharactersDialog,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun GridIcon(
    expanded: MutableState<Boolean>,
    settingViewModel: SettingViewModel,
    isGridView: Boolean
) {
    DropdownMenuItem(onClick = {
        expanded.value = false
        settingViewModel.toggleGridView()
    }) {
        Icon(
            imageVector = if (isGridView) Icons.Default.List else Icons.Default.Settings,
            contentDescription = "Toggle View",
            modifier = Modifier.padding(end = 8.dp),
            tint = MaterialTheme.colors.onSurface
        )
        Text(
            if (isGridView) "List View" else "Grid View",
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun ThemeIcon(
    expanded: MutableState<Boolean>,
    settingViewModel: SettingViewModel,
    isDarkTheme: Boolean
) {
    val animatedDarkTheme = remember { mutableStateOf(isDarkTheme) }

    DropdownMenuItem(onClick = {
        expanded.value = false
        animatedDarkTheme.value = !animatedDarkTheme.value
        settingViewModel.toggleTheme()
    }) {
        Crossfade(targetState = animatedDarkTheme.value, label = "Theme Icon Animation") { theme ->
            Icon(
                imageVector = if (theme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = "Theme Icon",
                modifier = Modifier.padding(end = 8.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }
        Text(
            if (animatedDarkTheme.value) "Light Mode" else "Dark Mode",
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun ProvideLazyColumn(state: CharactersViewModel.CharactersState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.characterList) { character ->
            CharacterItemList(
                character = character
            )
        }
    }
}

@Composable
private fun ProvideLazyVerticalGrid(state: CharactersViewModel.CharactersState) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(state.characterList) { character ->
            CharacterItemGrid(
                character = character,
            )
        }
    }
}

@Composable
private fun CountryDialog(
    characters: DetailedCharacters,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = characters.id,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = characters.name,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + characters.gender)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + characters.species)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + characters.id)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): ${characters.species}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}