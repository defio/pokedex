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
package com.nicoladefiorenze.pokedex.dagger

import com.nicoladefiorenze.pokedex.dagger.PokemonProviderModule
import com.nicoladefiorenze.pokedex.redux.ApplicationState
import com.nicoladefiorenze.pokedex.redux.FetchPokemonMiddleware
import com.nicoladefiorenze.pokedex.redux.Reducer
import com.nicoladefiorenze.pokedex.redux.createLoggerMiddleware
import com.nicoladefiorenze.pokedex.remote.PokemonRemoteProvider
import dagger.Module
import dagger.Provides
import redux.api.Store
import redux.applyMiddleware
import redux.createStore
import javax.inject.Singleton

@Module(includes = arrayOf(PokemonProviderModule::class))
class ReduxModule {

    @Provides
    @Singleton
    fun getStore(reducers: Reducer, initialState: ApplicationState, enhancer: Store.Enhancer<ApplicationState>): Store<ApplicationState> {
        return createStore(reducers, initialState, enhancer)
    }

    @Provides
    @Singleton
    fun getMiddleware(pokemonRemoteProvider: PokemonRemoteProvider) = applyMiddleware(FetchPokemonMiddleware(pokemonRemoteProvider), createLoggerMiddleware())

    @Provides
    fun getInitialState() = ApplicationState()

    @Provides
    @Singleton
    fun getReducer() = Reducer()

}