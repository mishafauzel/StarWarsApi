package com.example.starwarsapi.presentation.planets.adapter

import android.view.View
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class GetNextPageDataViewHolder(itemView: View) : GenericViewHolder<ItemUi>(itemView) {

    override fun bind(item: ItemUi) {
        item.show(
            with(itemView)
            {
                findViewById(R.id.yes)
            }
        )
    }
}