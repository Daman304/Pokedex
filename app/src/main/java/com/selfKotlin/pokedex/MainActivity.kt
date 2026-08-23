package com.selfKotlin.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.selfKotlin.pokedex.data.model.DummyPokemonData
import com.selfKotlin.pokedex.data.model.Pokemon
import com.selfKotlin.pokedex.ui.screens.PokedexScreen
import com.selfKotlin.pokedex.ui.screens.PokemonDetailScreen
import com.selfKotlin.pokedex.ui.theme.PokedexTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                PokedexTheme {
                    var selectedPokemon by remember {
                        mutableStateOf<Pokemon?>(null)
                    }
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ){
                        if (selectedPokemon==null){
                            PokedexScreen(
                                onPokemonClick = {pokemon->
                                    selectedPokemon=pokemon
                                }
                            )
                        }else{
                            PokemonDetailScreen(
                                pokemon = selectedPokemon!!,
                                onBackClick={
                                    selectedPokemon=null
                                }
                            )
                        }
                    }
                }
        }
    }
}