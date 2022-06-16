package com.example.starwarsapi.presentation.planets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.planets.items.PagerItem
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class ThereIsNoDataAnymoreViewHolderFactoryChain(private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>) :
    ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> {
        if (viewType == 6) {
            return ThereIsNoMoreDataViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.there_is_no_data, parent, false)
            )
        } else return viewHolderFactoryChain.viewHolder(parent, viewType)

    }
}