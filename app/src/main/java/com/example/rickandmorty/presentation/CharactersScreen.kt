package com.example.rickandmorty.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rickandmorty.domain.DetailedCharacters
import com.example.rickandmorty.domain.SimpleCharacter

@Composable
fun CharactersScreen(
    state: CharactersViewModel.CharactersState,
    onDismissCharactersDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("chaar","character.id")

        if(state.isLoading) {
            Log.d("chaar","if.id")

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Log.d("chaar","else.id")

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                Log.d("chaar","lazy.id")
                Log.d("chaar",state.characterList.toString())

                items(state.characterList) { character ->
                    Log.d("chaar","item.id")
                    CharacterItem(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }

            if(state.selectedCharacters != null) {
                CountryDialog(
                    characters = state.selectedCharacters,
                    onDismiss = onDismissCharactersDialog,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(16.dp)
                )
            }
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

@Composable
private fun CharacterItem(
    character: SimpleCharacter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = character.id,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = character.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = character. gender)
        }
    }
}