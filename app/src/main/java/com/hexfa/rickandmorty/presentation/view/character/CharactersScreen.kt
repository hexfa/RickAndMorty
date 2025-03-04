package com.hexfa.rickandmorty.presentation.view.character

import androidx.compose.animation.AnimatedContent
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

@Composable
fun CharactersScreen() {

    val viewModel: CharactersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        val expanded = remember { mutableStateOf(false) }
        val isGridView = remember { mutableStateOf(false) }

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
                    ThemeIcon(expanded)
                    GridIcon(expanded, isGridView)
                }
            }
        )



        AnimatedContent(targetState = isGridView.value, label = "List-Grid Animation") { gridView ->
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
    isGridView: MutableState<Boolean>
) {
    DropdownMenuItem(onClick = {
        expanded.value = false
        isGridView.value = !isGridView.value
    }) {
        Icon(
            imageVector = if (isGridView.value) Icons.Default.List else Icons.Default.Settings,
            contentDescription = "Toggle View",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(if (isGridView.value) "List View" else "Grid View")
    }
}

@Composable
private fun ThemeIcon(expanded: MutableState<Boolean>) {
    DropdownMenuItem(onClick = {
        expanded.value = false
    }) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Theme",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text("Theme")
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