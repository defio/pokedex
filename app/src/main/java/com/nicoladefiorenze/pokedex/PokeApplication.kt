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
package com.nicoladefiorenze.pokedex

import com.nicoladefiorenze.pokedex.inject.DaggerAppComponent
import com.nicoladefiorenze.pokedex.redux.PokeStore
import com.nicoladefiorenze.pokedex.redux.home.INIT
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCH
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class PokeApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        plantTimber()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        LeakCanary.install(this)

        val store = PokeStore().createStore()

        val subscription = store.subscribe {
//            System.out.println("-------------------")
//            System.out.println("New State: $it")
        }

        store.dispatch(INIT())
        store.dispatch(POKEMONS_FETCH)


    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}