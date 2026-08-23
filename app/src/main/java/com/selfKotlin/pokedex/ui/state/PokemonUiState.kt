package com.selfKotlin.pokedex.ui.state

import com.selfKotlin.pokedex.data.model.Pokemon

sealed interface PokemonUiState{
    data object Loading: PokemonUiState
    data class Success(
        val pokemon: List<Pokemon>,
        val isLoadingMore: Boolean=false,
        val loadMoreError: String? = null
    ): PokemonUiState
    data class Error(
        val msg: String
    ): PokemonUiState
}