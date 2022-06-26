package com.example.starwarsapi.presentation.planets.basedata

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.core.Mapper as UnitMapper

interface PlanetsUi : UnitMapper.Unit<UnitMapper.Unit<List<ItemUi>>> {
    fun <T> map(mapper: Mapper<T>): T

     data class Base(private val listOfItems: List<ItemUi>) :
        PlanetsUi {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(listOfItems)


        override fun map(data: UnitMapper.Unit<List<ItemUi>>) {
            data.map(listOfItems)
        }

    }
    data class ExceptionUI(private val itemUI:ItemUi):PlanetsUi
    {
        override fun <T> map(mapper: Mapper<T>): T {
            TODO("Not yet implemented")
        }

        override fun map(data: com.github.johnnysc.coremvvm.core.Mapper.Unit<List<ItemUi>>) {
            TODO("Not yet implemented")
        }
    }

    interface Mapper<T> {

        fun map(planetUI: List<ItemUi>): T

        class Base : Mapper<List<ItemUi>> {

            override fun map(planetUI: List<ItemUi>) = planetUI

        }
    }
}