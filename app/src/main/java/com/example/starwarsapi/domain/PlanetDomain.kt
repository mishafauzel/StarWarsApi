package com.example.starwarsapi.domain

import com.example.starwarsapi.presentation.planets.PlanetUI

interface PlanetDomain {
    fun<T> map(mapper: Mapper<T>):T

    class Base(private val id:Int,private val name:String): PlanetDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(id,name)
        }
    }

    interface Mapper<T>
    {
        fun map(id:Int,name: String):T
        class BaseToUI: Mapper<PlanetUI>
        {
            override fun map(id: Int, name: String): PlanetUI {
                return PlanetUI(id, name)
            }
        }
    }
}