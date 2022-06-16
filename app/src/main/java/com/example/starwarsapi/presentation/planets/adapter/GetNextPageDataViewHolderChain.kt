package com.example.starwarsapi.presentation.planets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class GetNextPageDataViewHolderChain (
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
): ViewHolderFactoryChain<ItemUi> {
    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> {
        return if(viewType==4)
            GetNextPageDataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.do_uou_want_know_more,parent,false))
        else
            viewHolderFactoryChain.viewHolder(parent,viewType)
    }
}