package com.example.starwarsapi.presentation.planets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class ThereIsNoDataAnymoreViewHolderFactoryChain(private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>) :
    ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int) = if (viewType == 6) {
        ThereIsNoMoreDataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.there_is_no_data, parent, false)
        )
    }
    else viewHolderFactoryChain.viewHolder(parent, viewType)

}
