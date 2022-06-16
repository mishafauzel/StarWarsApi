package com.example.starwarsapi.presentation.planets.adapter

import android.view.View
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EmptyCharacterListViewHolder(view: View) : GenericViewHolder<ItemUi>(view) {
    override fun bind(item: ItemUi) {
        return
    }
}