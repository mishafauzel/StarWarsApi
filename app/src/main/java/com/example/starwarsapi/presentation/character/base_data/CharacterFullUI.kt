package com.example.starwarsapi.presentation.character.base_data

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface CharacterFullUI : Mapper.Unit<Mapper.Unit<List<ItemUi>>> {

    data class Base(private val characterFullInfoItem: List<ItemUi>) : CharacterFullUI {

        override fun map(data: Mapper.Unit<List<ItemUi>>) {
            data.map(characterFullInfoItem)
        }

    }

}