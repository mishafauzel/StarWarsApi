package com.example.starwarsapi.data.planets.cloud.planets

import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.google.gson.annotations.SerializedName

interface PlanetsCloud {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("next")
        private val next: String?,
        @SerializedName("results")
        private val listOfPlanets: List<PlanetCloud.Base>
    ) : PlanetsCloud {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(next, listOfPlanets)

    }

    interface Mapper<T> {
        fun map(next: String?, listOfPlanets: List<PlanetCloud>): T

        class BaseToCache(
            private val currentPage: Int,
            private val mapperFactory: PlanetCloud.Mapper.Factory<PlanetCache>
        ) : Mapper<PlanetsCache> {

            override fun map(next: String?, listOfPlanets: List<PlanetCloud>) =
                PlanetsCache.Base(listOfPlanets = listOfPlanets.map { planetCloud ->
                    planetCloud.map(mapperFactory.create(currentPage, next ?: ""))
                })

        }

        class BaseToListCharacters(private val planetToListCharacters: PlanetCloud.Mapper<List<CharacterCache>>) :
            Mapper<List<CharacterCache>> {

            override fun map(
                next: String?,
                listOfPlanets: List<PlanetCloud>
            ): List<CharacterCache> {
                val mutableList = mutableListOf<CharacterCache>()
                listOfPlanets.forEach { planetCloud ->
                    mutableList.addAll(planetCloud.map(planetToListCharacters))
                }
                return mutableList.toList()
            }

        }

        interface Factory<T> {
            fun create(currentPage: Int): Mapper<T>
            class Base(private val planetCloudToCacheMapper: PlanetCloud.Mapper.Factory<PlanetCache>) :
                Factory<PlanetsCache> {
                override fun create(currentPage: Int) =
                    BaseToCache(currentPage, planetCloudToCacheMapper)

            }
        }
    }
}