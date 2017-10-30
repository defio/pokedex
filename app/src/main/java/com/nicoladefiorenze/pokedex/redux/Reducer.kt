/*
 * Copyright 2017 Nicola De Fiorenze
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.nicoladefiorenze.pokedex.redux

import com.nicoladefiorenze.pokedex.PokemonsQuery
import com.nicoladefiorenze.pokedex.data.buisiness.PokemonAbstract
import com.nicoladefiorenze.pokedex.data.buisiness.PokemonListState
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCHED_ERROR
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCHED_FULLFILED
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCHING

class Reducer : redux.api.Reducer<ApplicationState> {

    override fun reduce(state: ApplicationState, action: Any): ApplicationState {
        return when (action) {
            is POKEMONS_FETCHING -> state.copy(pokemonListState = PokemonListState.FETCHING)
            is POKEMONS_FETCHED_ERROR -> state.copy(pokemonListState = PokemonListState.FETCHED_ERROR)
            is POKEMONS_FETCHED_FULLFILED -> state.copy(pokemonListState = PokemonListState.FETCHED_FULFILLED,
                    pokemonList = action.data?.toPokemonAbstractList() ?: emptyList())
            else -> state
        }
    }

}

private fun List<PokemonsQuery.Pokemon>.toPokemonAbstractList(): List<PokemonAbstract> {
    return this.filter {
        val retrievedName = it.name()
        val retrievedNumber = it.number()
        val retrievedImage = it.image()

        retrievedImage != null && retrievedName != null && retrievedNumber!=null
    }.map {
        val retrievedNumber = it.number()!!
        val retrievedName = it.name()!!
        val retrievedImage = it.image()!!

        PokemonAbstract(retrievedNumber.toInt(), retrievedName, retrievedImage)
    }
}

