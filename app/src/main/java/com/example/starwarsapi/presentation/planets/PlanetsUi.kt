package com.example.starwarsapi.presentation.planets

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PlanetsUi :Mapper.Unit<Mapper.Unit<List<ItemUi>>>{
    class Base(private val pagerItem: PagerItem, private val planetUI: List<ItemUi>): PlanetsUi {
        override fun map(data: Mapper.Unit<List<ItemUi>>) {
            data.map(planetUI)
        }
    }
    class Exception(private val somethingWentWrong:List<ItemUi>):PlanetsUi
    {
        override fun map(data: Mapper.Unit<List<ItemUi>>) {
            data.map(somethingWentWrong)
        }
    }
}