package com.example.starwarsapi.presentation.planets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class SomethingWentWrongViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : ViewHolderFactoryChain<ItemUi> {
    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> {
        return when (viewType) {
            5 -> SomethingWentWrongViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.something_went_wrong, parent, false)
            )
            else -> viewHolderFactoryChain.viewHolder(parent, viewType)
        }
    }
}