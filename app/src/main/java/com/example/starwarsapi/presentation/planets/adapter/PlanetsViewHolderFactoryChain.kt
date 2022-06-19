package com.example.starwarsapi.presentation.planets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class PlanetsViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : ViewHolderFactoryChain<ItemUi> {
    override fun viewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        1 -> PlanetsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.planet_item, parent, false)
        )
        else -> viewHolderFactoryChain.viewHolder(parent, viewType)
    }
}
