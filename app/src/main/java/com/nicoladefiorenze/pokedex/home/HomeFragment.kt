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
package com.nicoladefiorenze.pokedex.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicoladefiorenze.pokedex.PokeFragment
import com.nicoladefiorenze.pokedex.R
import com.nicoladefiorenze.pokedex.home.adapter.PokemonsAdapter
import com.nicoladefiorenze.pokedex.redux.ApplicationState
import com.nicoladefiorenze.pokedex.redux.extension.asLiveData
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCH
import kotlinx.android.synthetic.main.home_fragment.*
import redux.api.Store
import redux.asObservable
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : PokeFragment() {

    @Inject lateinit var store: Store<ApplicationState>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userViewModel: HomeViewModel
    private val groupAdapter = PokemonsAdapter()
    private var storeLiveData: LiveData<ApplicationState>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        storeLiveData = store.asLiveData()

        store.asObservable().subscribe({
            Timber.d("...> lifecycle currentState ${this.lifecycle.currentState}")
            Timber.d("...> application state $it")
        })
    }

    override fun onResume() {
        super.onResume()
        store.dispatch(POKEMONS_FETCH)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        pokemon_recycler_view.setHasFixedSize(true)

        linearLayoutManager = pokemon_recycler_view.layoutManager as LinearLayoutManager
        pokemon_recycler_view.adapter = groupAdapter
    }
}

