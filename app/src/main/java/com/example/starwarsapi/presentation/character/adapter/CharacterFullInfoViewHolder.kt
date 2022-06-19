package com.example.starwarsapi.presentation.character.adapter

import android.view.View
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class CharacterFullInfoViewHolder(view: View) : GenericViewHolder<ItemUi>(view) {

    override fun bind(item: ItemUi) {
        with(itemView)
        {
            item.show(
                this.findViewById(R.id.name),
                this.findViewById(R.id.birth_year),
                this.findViewById(R.id.hair_color),
                this.findViewById(R.id.skin_color),
                this.findViewById(R.id.gender),
                this.findViewById(R.id.mass),
                this.findViewById(R.id.height)
            )
        }

    }
}