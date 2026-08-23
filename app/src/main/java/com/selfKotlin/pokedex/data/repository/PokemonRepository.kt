package com.selfKotlin.pokedex.data.repository

import androidx.compose.ui.graphics.Color
import com.selfKotlin.pokedex.data.model.Pokemon
import com.selfKotlin.pokedex.data.model.PokemonStat
import com.selfKotlin.pokedex.data.remote.RetrofitClient

class PokemonRepository {

    suspend fun getPokemon(query: String): Pokemon {

        val response = RetrofitClient.api.getPokemon(query)

        return Pokemon(
            id = response.id,
            name = response.name.replaceFirstChar {
                it.uppercase()
            },
            imageUrl = response.sprites.other.officialArtwork.frontDefault
                ?: "",
            color = getPokemonColor(response.types.firstOrNull()?.type?.name),
            weight = response.weight / 10.0,
            height = response.height / 10.0,
            types = response.types.map { pokemonType ->
                pokemonType.type.name.replaceFirstChar {
                    it.uppercase()
                }
            },
            stats = response.stats.map { stat ->
                PokemonStat(
                    name = formatStatName(stat.stat.name),
                    value = stat.baseStat
                )
            }
        )
    }
    suspend fun getPokemon(id: Int): Pokemon {
        return getPokemon(id.toString())
    }

    private fun getPokemonColor(type: String?): Color {
        return when (type) {
            "grass" -> Color(0xFF78C6A3)
            "fire" -> Color(0xFFFFA45B)
            "water" -> Color(0xFF7AA9C2)
            "electric" -> Color(0xFFFFD166)
            "psychic" -> Color(0xFFB388EB)
            "ice" -> Color(0xFF80DEEA)
            "fighting" -> Color(0xFFE57373)
            "poison" -> Color(0xFFBA68C8)
            "ground" -> Color(0xFFD4A373)
            "rock" -> Color(0xFFBCAAA4)
            "bug" -> Color(0xFFA5D66A)
            "ghost" -> Color(0xFF9575CD)
            "dragon" -> Color(0xFF7986CB)
            "dark" -> Color(0xFF795548)
            "steel" -> Color(0xFF90A4AE)
            "fairy" -> Color(0xFFF48FB1)
            else -> Color(0xFFBDBDBD)
        }
    }

    private fun formatStatName(name: String): String {
        return when (name) {
            "hp" -> "HP"
            "attack" -> "Attack"
            "defense" -> "Defense"
            "special-attack" -> "Sp. Atk"
            "special-defense" -> "Sp. Def"
            "speed" -> "Speed"
            else -> name
        }
    }
}