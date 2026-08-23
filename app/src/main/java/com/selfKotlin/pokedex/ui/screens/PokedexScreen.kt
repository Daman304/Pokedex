package com.selfKotlin.pokedex.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfKotlin.pokedex.data.model.DummyPokemonData
import com.selfKotlin.pokedex.data.model.Pokemon
import com.selfKotlin.pokedex.ui.components.PokemonCard
import com.selfKotlin.pokedex.ui.state.PokemonUiState
import com.selfKotlin.pokedex.viewModels.PokemonViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun PokedexScreen(
    onPokemonClick: (Pokemon)-> Unit,
    viewModel: PokemonViewModel= viewModel()
){
    val savedIndex = viewModel.getSavedScrollIndex()
    val savedOffset = viewModel.getSavedScrollOffset()

    val gridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = savedIndex,
        initialFirstVisibleItemScrollOffset = savedOffset
    )
    var searchQuery by remember { mutableStateOf("") }
    val shouldLoadMore = remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isNotBlank()) {
                false
            } else {
                val layoutInfo = gridState.layoutInfo
                val lastVisibleItem =
                    layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                lastVisibleItem >= layoutInfo.totalItemsCount - 5
            }
        }
    }
    LaunchedEffect(gridState) {
        snapshotFlow { shouldLoadMore.value }.distinctUntilChanged().collect{shouldLoad->
            if (shouldLoad){
                viewModel.loadMorePokemon()
            }
        }
    }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPokemon()
    }
    when(val state = uiState){
        PokemonUiState.Loading->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is PokemonUiState.Success -> {

            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1E1E1E)),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 20.dp,
                    bottom = 24.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item(
                    span = { GridItemSpan(2) }
                ) {

                    Column {

                        Text(
                            text = "Pokédex",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = "Explore the Pokémon collection",
                            fontSize = 14.sp,
                            color = Color(0xFFAAAAAA),
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = Color(0xFF888888),
                                unfocusedBorderColor = Color(0xFF555555),
                                focusedLeadingIconColor = Color.White,
                                unfocusedLeadingIconColor = Color(0xFFAAAAAA),
                                cursorColor = Color.White
                            ),
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                                viewModel.searchPokemon(it)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("Search Pokémon...")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(
                                        onClick = {
                                            searchQuery = ""
                                            viewModel.searchPokemon("")
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "Clear search"
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(18.dp)
                        )
                    }
                }

                items(
                    items = state.pokemon,
                    key = { pokemon -> pokemon.id }
                ) { pokemon ->

                    var visible by remember(pokemon.id) {
                        mutableStateOf(false)
                    }

                    LaunchedEffect(pokemon.id) {
                        visible = true
                    }

                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(
                            animationSpec = tween(250)
                        ) + slideInVertically(
                            initialOffsetY = { it / 4 },
                            animationSpec = tween(250)
                        ),
                        exit = fadeOut(
                            animationSpec = tween(150)
                        )
                    ) {
                        PokemonCard(
                            pokemon = pokemon,
                            onClick = {
                                viewModel.saveScrollPosition(
                                    index = gridState.firstVisibleItemIndex,
                                    offset = gridState.firstVisibleItemScrollOffset
                                )

                                onPokemonClick(pokemon)
                            }
                        )
                    }
                }

                if (state.isLoadingMore) {

                    item(
                        span = { GridItemSpan(2) }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (state.loadMoreError != null) {

                    item(
                        span = { GridItemSpan(2) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text("Couldn't load more Pokémon 😭")

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Button(
                                onClick = {
                                    viewModel.loadMorePokemon()
                                }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }
        }
        is PokemonUiState.Error ->{
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Couldn't load Pokémon 😭"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = state.msg
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = {
                        viewModel.loadPokemon()
                    }
                ) {
                    Text("Retry")
                }
            }
        }
    }
}