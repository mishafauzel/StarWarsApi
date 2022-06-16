package com.example.starwarsapi.presentation.planets.basedata

import com.github.johnnysc.coremvvm.core.Mapper as UnitMapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PlanetsUi :UnitMapper.Unit<UnitMapper.Unit<List<ItemUi>>>{
    fun<T> map(mapper: Mapper<T>):T
    data class Base(private val planetUI: List<ItemUi>):
        PlanetsUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(planetUI)
        }


        override fun map(data: UnitMapper.Unit<List<ItemUi>>) {
            data.map(planetUI)
        }
    }

    interface Mapper<T>
    {
        fun map( planetUI: List<ItemUi>):T
        class Base: Mapper<List<ItemUi>>
        {
            override fun map( planetUI: List<ItemUi>): List<ItemUi> {
                return planetUI
            }
        }

    }
}