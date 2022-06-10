package com.example.starwarsapi.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.characters.adapter.CharactersViewHolder
import com.example.starwarsapi.presentation.planets.adapter.PlanetsViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class CharacterViewHolderFactoryChain (
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
): ViewHolderFactoryChain<ItemUi> {
    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> {
        when(viewType)
        {
            2->return CharactersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item,parent,false))
            else->return viewHolderFactoryChain.viewHolder(parent,viewType)
        }
    }
}