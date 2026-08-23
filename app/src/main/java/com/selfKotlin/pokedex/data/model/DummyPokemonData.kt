package com.selfKotlin.pokedex.data.model

import androidx.compose.ui.graphics.Color

object DummyPokemonData {
    val pokemonList = listOf(

        Pokemon(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            color = Color(0xFF78C6A3),
            weight = 6.9,
            height = 0.7,
            types = listOf("Grass", "Poison"),
            stats = listOf(
                PokemonStat("HP", 45),
                PokemonStat("Attack", 49),
                PokemonStat("Defense", 49),
                PokemonStat("Sp. Atk", 65),
                PokemonStat("Sp. Def", 65),
                PokemonStat("Speed", 45)
            )
        ),

        Pokemon(
            id = 2,
            name = "Ivysaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png",
            color = Color(0xFF5F9EAD),
            weight = 13.0,
            height = 1.0,
            types = listOf("Grass", "Poison"),
            stats = listOf(
                PokemonStat("HP", 60),
                PokemonStat("Attack", 62),
                PokemonStat("Defense", 63),
                PokemonStat("Sp. Atk", 80),
                PokemonStat("Sp. Def", 80),
                PokemonStat("Speed", 60)
            )
        ),

        Pokemon(
            id = 3,
            name = "Venusaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png",
            color = Color(0xFF4E9B8A),
            weight = 100.0,
            height = 2.0,
            types = listOf("Grass", "Poison"),
            stats = listOf(
                PokemonStat("HP", 80),
                PokemonStat("Attack", 82),
                PokemonStat("Defense", 83),
                PokemonStat("Sp. Atk", 100),
                PokemonStat("Sp. Def", 100),
                PokemonStat("Speed", 80)
            )
        ),

        Pokemon(
            id = 4,
            name = "Charmander",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
            color = Color(0xFFFFD28A),
            weight = 8.5,
            height = 0.6,
            types = listOf("Fire"),
            stats = listOf(
                PokemonStat("HP", 39),
                PokemonStat("Attack", 52),
                PokemonStat("Defense", 43),
                PokemonStat("Sp. Atk", 60),
                PokemonStat("Sp. Def", 50),
                PokemonStat("Speed", 65)
            )
        ),

        Pokemon(
            id = 5,
            name = "Charmeleon",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png",
            color = Color(0xFFD96B57),
            weight = 19.0,
            height = 1.1,
            types = listOf("Fire"),
            stats = listOf(
                PokemonStat("HP", 58),
                PokemonStat("Attack", 64),
                PokemonStat("Defense", 58),
                PokemonStat("Sp. Atk", 80),
                PokemonStat("Sp. Def", 65),
                PokemonStat("Speed", 80)
            )
        ),

        Pokemon(
            id = 6,
            name = "Charizard",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",
            color = Color(0xFFFFA45B),
            weight = 90.5,
            height = 1.7,
            types = listOf("Fire", "Flying"),
            stats = listOf(
                PokemonStat("HP", 78),
                PokemonStat("Attack", 84),
                PokemonStat("Defense", 78),
                PokemonStat("Sp. Atk", 109),
                PokemonStat("Sp. Def", 85),
                PokemonStat("Speed", 100)
            )
        ),

        Pokemon(
            id = 7,
            name = "Squirtle",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
            color = Color(0xFF7AA9C2),
            weight = 9.0,
            height = 0.5,
            types = listOf("Water"),
            stats = listOf(
                PokemonStat("HP", 44),
                PokemonStat("Attack", 48),
                PokemonStat("Defense", 65),
                PokemonStat("Sp. Atk", 50),
                PokemonStat("Sp. Def", 64),
                PokemonStat("Speed", 43)
            )
        ),

        Pokemon(
            id = 8,
            name = "Wartortle",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png",
            color = Color(0xFF9AA8C4),
            weight = 22.5,
            height = 1.0,
            types = listOf("Water"),
            stats = listOf(
                PokemonStat("HP", 59),
                PokemonStat("Attack", 63),
                PokemonStat("Defense", 80),
                PokemonStat("Sp. Atk", 65),
                PokemonStat("Sp. Def", 80),
                PokemonStat("Speed", 58)
            )
        ),

        Pokemon(
            id = 9,
            name = "Blastoise",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png",
            color = Color(0xFF6E91B8),
            weight = 85.5,
            height = 1.6,
            types = listOf("Water"),
            stats = listOf(
                PokemonStat("HP", 79),
                PokemonStat("Attack", 83),
                PokemonStat("Defense", 100),
                PokemonStat("Sp. Atk", 85),
                PokemonStat("Sp. Def", 105),
                PokemonStat("Speed", 78)
            )
        )
    )
}