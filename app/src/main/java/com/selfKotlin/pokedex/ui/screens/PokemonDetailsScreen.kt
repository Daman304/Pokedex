package com.selfKotlin.pokedex.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.selfKotlin.pokedex.data.model.Pokemon
import com.selfKotlin.pokedex.ui.components.StatBar
import okhttp3.internal.wait

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon,
    onBackClick: ()->Unit
){
    val screenAlpha = remember {
        Animatable(0f)
    }

    val screenOffset = remember {
        Animatable(40f)
    }

    LaunchedEffect(pokemon.id) {

        screenAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    LaunchedEffect(pokemon.id) {

        screenOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 350,
                easing = FastOutSlowInEasing
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF2B292C)).graphicsLayer{
            alpha = screenAlpha.value
            translationY = screenOffset.value
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth().height(330.dp).clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)).background(pokemon.color), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Text(
                        text = "Pokedex",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
                Text(
                    text = "#%03d".format(pokemon.id),
                    color = Color.White
                )
            }
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(230.dp),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = pokemon.name,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            pokemon.types.forEach { type->
                Text(
                    text = type,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 6.dp).clip(RoundedCornerShape(20.dp)).background(Color.Gray).padding(horizontal = 24.dp, vertical = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${pokemon.weight} KG",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Weight",
                    color = Color.Gray
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${pokemon.height} m",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Height",
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Base Stats",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            pokemon.stats.forEach {
                stat ->
                StatBar(
                    statName = stat.name,
                    statValue = stat.value
                )
            }
        }
    }
}