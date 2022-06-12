package com.example.starwarsapi.presentation.planets.adapter

import android.view.View
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class CharactersViewHolder(view: View): GenericViewHolder<ItemUi>(view) {
    override fun bind(item: ItemUi) {
        with(item)
        {
            this.show(itemView.findViewById(R.id.name),itemView.findViewById(R.id.birth_year),itemView.findViewById(R.id.container))

        }
    }
}