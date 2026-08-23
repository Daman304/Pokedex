package com.selfKotlin.pokedex.ui.components

import android.view.RoundedCorner
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.selfKotlin.pokedex.data.model.Pokemon
import java.nio.file.WatchEvent

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick:()->Unit
){

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(120),
        label = "PokemonCardScale"
    )
    Column(
        modifier = Modifier.fillMaxWidth().height(220.dp).scale(scale).clip(RoundedCornerShape(16.dp)).background(pokemon.color).clickable(
            onClick=onClick
        ).padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model=pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text=pokemon.name,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}