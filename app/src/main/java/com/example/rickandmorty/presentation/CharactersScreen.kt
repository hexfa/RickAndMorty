package com.example.rickandmorty.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmorty.domain.model.DetailedCharacters
import com.example.rickandmorty.domain.model.Character

@Composable
fun CharactersScreen(
    state: CharactersViewModel.CharactersState,
    onDismissCharactersDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
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
    character: Character,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp)),
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image,),
                contentDescription = character.id,
                modifier = Modifier.height(140.dp).width(140.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
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
                Text(text = character.gender)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = character.species)
            }
        }
    }
}
@Composable
fun ItemCard(name: String, pictureUrl: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter(pictureUrl),
            contentDescription = name,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(text = name, modifier = Modifier.padding(top = 8.dp))
    }
}