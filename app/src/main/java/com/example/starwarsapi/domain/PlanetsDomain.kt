package com.example.starwarsapi.domain

import com.example.starwarsapi.presentation.planets.PagerUI
import com.example.starwarsapi.presentation.planets.PlanetUI
import com.example.starwarsapi.presentation.planets.PlanetsUi

interface PlanetsDomain
{
    fun<T> map(mapper: Mapper<T>):T
    class Base(private val pagerDomain: PagerDomain, private val planetDomain: List<PlanetDomain>):
        PlanetsDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(pagerDomain,planetDomain)
        }
    }

    interface Mapper<T>
    {
        fun map(pagerDomain: PagerDomain, planetDomain: List<PlanetDomain>):T
        class BaseToUI(private val planetMapper: PlanetDomain.Mapper<PlanetUI>, private val pagerMapper: PagerDomain.Mapper<PagerUI>):
            Mapper<PlanetsUi>
        {
            override fun map(
                pagerDomain: PagerDomain,
                planetDomain: List<PlanetDomain>
            ): PlanetsUi {
                return PlanetsUi.Base(pagerDomain.map(pagerMapper),planetDomain.map { planetDomain ->
                    planetDomain.map(planetMapper)
                })
            }

        }
    }
}