package com.example.starwarsapi.data.planets.cache.planets

import com.example.starwarsapi.core.IsEmpty
import com.example.starwarsapi.domain.planets.PagerDomain
import com.example.starwarsapi.domain.planets.PlanetDomain
import com.example.starwarsapi.domain.planets.PlanetsDomain

interface PlanetsCache : IsEmpty {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val listOfPlanets: List<PlanetCache>) : PlanetsCache {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(listOfPlanets = listOfPlanets)

        override fun isEmpty() = listOfPlanets.isEmpty()

    }

    interface Mapper<T> {

        fun map(listOfPlanets: List<PlanetCache>): T

        class BaseToList() : Mapper<List<PlanetCache>> {

            override fun map(listOfPlanets: List<PlanetCache>) = listOfPlanets

        }

        class BaseToDomain(
            private val mapperToDomain: PlanetCache.Mapper<PlanetDomain>,
            private val mapperToPagerDomain: PlanetCache.Mapper<PagerDomain>
        ) : Mapper<PlanetsDomain> {

            override fun map(listOfPlanets: List<PlanetCache>) = PlanetsDomain.Base(
                listOfPlanets.first().map(mapperToPagerDomain),
                listOfPlanets.map { planetCache ->
                    planetCache.map(mapperToDomain)
                })

        }
    }
}

