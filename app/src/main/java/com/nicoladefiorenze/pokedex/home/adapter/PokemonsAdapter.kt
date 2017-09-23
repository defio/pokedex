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
package com.nicoladefiorenze.pokedex.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nicoladefiorenze.pokedex.R
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonsAdapter : RecyclerView.Adapter<PokemonsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: PokemonsAdapter.ViewHolder, position: Int) {
        holder.name.text = "$position"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.pokemon_item, parent, false))

    }

    override fun getItemCount(): Int {
        return 100
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.pokemon_name
    }
}
