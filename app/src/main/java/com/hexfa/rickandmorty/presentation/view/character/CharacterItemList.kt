package com.hexfa.rickandmorty.presentation.view.character

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.hexfa.rickandmorty.domain.model.Character
import com.hexfa.rickandmorty.ui.theme.FemaleColor
import com.hexfa.rickandmorty.ui.theme.MaleColor

@SuppressLint("DiscouragedApi")
@Composable
fun CharacterItemList(
    character: Character,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.onBackground, RoundedCornerShape(8.dp)),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //todo mock data
            val context = LocalContext.current
            val imageId =
                context.resources.getIdentifier("cartoon", "drawable", context.packageName)

            if (imageId != 0) {
                Image(
                    painter = rememberAsyncImagePainter(character.image),
                    contentDescription = character.id,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = character.gender,
                    color = if (character.gender == "Female") FemaleColor else MaleColor
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = character.species, color = MaterialTheme.colors.secondaryVariant)
            }
        }
    }
}