package com.example.starwarsapi.presentation.planets.adapter

import android.view.View
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class SomethingWentWrongViewHolder(view: View) : GenericViewHolder<ItemUi>(view) {

    override fun bind(item: ItemUi) {
        with(itemView)
        {
            item.show(findViewById(R.id.retry), findViewById(R.id.error_message))
        }
    }
}