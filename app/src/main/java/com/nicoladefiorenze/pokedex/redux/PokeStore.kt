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

import com.brianegan.bansa.*

class PokeStore : Store<ApplicationState> {

    private lateinit var instance: BaseStore<ApplicationState>

    fun createStore(): BaseStore<ApplicationState> {
        val reducer = Reducer()
        this.instance = BaseStore(ApplicationState(), reducer, FetchPokemonMiddleware(),LoggingMiddleware())
        return instance
    }

    override fun getState(): ApplicationState {
        return instance.state
    }

    override fun subscribe(subscriber: Subscriber<ApplicationState>?): Subscription {
        return instance.subscribe(subscriber)
    }

    override fun dispatch(action: Action?): ApplicationState {
        return instance.dispatch(action)
    }

}