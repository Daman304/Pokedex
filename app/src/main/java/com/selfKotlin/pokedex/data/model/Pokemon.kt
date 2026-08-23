package com.selfKotlin.pokedex.data.model

import androidx.compose.ui.graphics.Color

data class Pokemon(
    val id:Int,
    val name:String,
    val imageUrl: String,
    val color: Color,
    val weight: Double,
    val height: Double,
    val types: List<String>,
    val stats: List<PokemonStat>
)