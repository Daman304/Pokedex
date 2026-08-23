package com.selfKotlin.pokedex.data.remote

import com.selfKotlin.pokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService{
    @GET("pokemon/{query}")
    suspend fun getPokemon(
        @Path("query") query:String
    ): PokemonResponse
}