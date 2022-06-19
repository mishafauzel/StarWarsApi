package com.example.starwarsapi.presentation.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class CharacterFullinfoViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        4 -> CharacterFullInfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_full_info, parent, false)
        )
        else -> viewHolderFactoryChain.viewHolder(parent, viewType)
    }

}
