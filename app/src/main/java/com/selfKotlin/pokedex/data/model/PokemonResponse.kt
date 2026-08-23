package com.selfKotlin.pokedex.data.model


import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<PokemonType>,
    val stats: List<PokemonStatResponse>
)

data class Sprites(
    val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class PokemonType(
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class PokemonStatResponse(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)