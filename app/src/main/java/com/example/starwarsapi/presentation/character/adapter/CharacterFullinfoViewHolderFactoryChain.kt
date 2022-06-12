package com.example.starwarsapi.presentation.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.planets.adapter.CharactersViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class CharacterFullinfoViewHolderFactoryChain (
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
): ViewHolderFactoryChain<ItemUi> {
    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> {
        return when(viewType)
        {
            4->return CharacterFullInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_full_info,parent,false))
            else->viewHolderFactoryChain.viewHolder(parent,viewType)
        }
    }
}