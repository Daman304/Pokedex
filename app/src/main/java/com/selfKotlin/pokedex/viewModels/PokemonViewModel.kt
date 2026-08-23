package com.selfKotlin.pokedex.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfKotlin.pokedex.data.model.Pokemon
import com.selfKotlin.pokedex.data.repository.PokemonRepository
import com.selfKotlin.pokedex.ui.state.PokemonUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val repository= PokemonRepository()
    private val _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()
    private var currentPage= 0
    private var searchJob: Job? = null
    private val pageSize = 20
    private var loadedPokemon = emptyList<Pokemon>()
    private var savedScrollIndex = 0
    private var savedScrollOffset = 0
    fun loadPokemon(){
        if(_uiState.value is PokemonUiState.Success) return
        viewModelScope.launch {
            _uiState.value= PokemonUiState.Loading
            try {
                val pokemonList = loadPokemonPage(1,pageSize)
                loadedPokemon=pokemonList
                currentPage=1
                _uiState.value= PokemonUiState.Success(pokemon=loadedPokemon)
            }catch (e: Exception){
                _uiState.value= PokemonUiState.Error(msg = e.message?:"Something went wrong")
            }
        }
    }
    fun loadMorePokemon(){
        val currentState =  _uiState.value
        if(currentState !is PokemonUiState.Success) return
        if (currentState.isLoadingMore) return
        viewModelScope.launch {
            _uiState.value=currentState.copy(isLoadingMore = true, loadMoreError = null)
            try {
                val startId = currentPage * pageSize+1
                val endId = startId + pageSize-1
                val newPokemon = loadPokemonPage(startId,endId)
                loadedPokemon=loadedPokemon+newPokemon
                currentPage++
                _uiState.value= PokemonUiState.Success(pokemon = loadedPokemon, isLoadingMore = false, loadMoreError = null)
            }catch (e: Exception){
                _uiState.value=currentState.copy(isLoadingMore = false, loadMoreError = e.message?:"Couldn't Load more Pokemon")
            }
        }
    }
    private suspend fun loadPokemonPage(
        startId:Int,
        endId: Int
    ): List<Pokemon> = coroutineScope{
        (startId..endId).map { id->
            async {
                repository.getPokemon(id)
            }
        }.awaitAll()
    }
    fun searchPokemon(query: String) {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            val trimmedQuery = query.trim()

            if (trimmedQuery.isBlank()) {

                val currentState = _uiState.value

                if (currentState is PokemonUiState.Success) {
                    _uiState.value = currentState.copy(
                        pokemon = loadedPokemon,
                        loadMoreError = null
                    )
                }

                return@launch
            }

            delay(400)

            val currentState = _uiState.value

            if (currentState is PokemonUiState.Success) {
                val localResults = loadedPokemon.filter { pokemon ->
                    pokemon.name.contains(
                        trimmedQuery,
                        ignoreCase = true
                    )
                }

                if (localResults.isNotEmpty()) {

                    _uiState.value = currentState.copy(
                        pokemon = localResults
                    )

                    return@launch
                }
                try {

                    val pokemon = repository.getPokemon(
                        trimmedQuery.lowercase()
                    )

                    _uiState.value = currentState.copy(
                        pokemon = listOf(pokemon)
                    )

                } catch (e: Exception) {

                    _uiState.value = currentState.copy(
                        pokemon = emptyList()
                    )
                }
            }
        }
    }
    fun saveScrollPosition(index: Int, offset: Int) {
        savedScrollIndex = index
        savedScrollOffset = offset
    }

    fun getSavedScrollIndex(): Int {
        return savedScrollIndex
    }

    fun getSavedScrollOffset(): Int {
        return savedScrollOffset
    }
}