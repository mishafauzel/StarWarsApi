package com.example.starwarsapi.data.cloud

import com.example.starwarsapi.data.cache.PlanetCache
import com.example.starwarsapi.data.cache.PlanetsCache
import com.google.gson.annotations.SerializedName

interface PlanetsCloud {
    fun <T> map(mapper: Mapper<T>): T
    class Base(
        @SerializedName("next")
        private val next: String,
        @SerializedName("results")
        private val listOfPlanets: List<PlanetCloud>
    ) : PlanetsCloud {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(next, listOfPlanets)
        }
    }

    interface Mapper<T> {
        fun map(next: String, listOfPlanets: List<PlanetCloud>): T

        class BaseToCache(
            private val currentPage: Int,
            private val mapperFactory: PlanetCloud.Mapper.Factory<PlanetCache>
        ) : Mapper<PlanetsCache> {
            override fun map(next: String, listOfPlanets: List<PlanetCloud>): PlanetsCache {
                val mapper = mapperFactory.create(currentPage, next)
                return PlanetsCache.Base(listOfPlanets = listOfPlanets.map { planetCloud ->
                    planetCloud.map(mapper)
                })

            }
        }
        interface Factory<T>{
            fun create(currentPage: Int): Mapper<T>
            class Base(private val planetMapperFactory: PlanetCloud.Mapper.Factory<PlanetCache>):
                Factory<PlanetsCache>
            {
                override fun create(currentPage: Int): Mapper<PlanetsCache> {
                    return BaseToCache(currentPage,planetMapperFactory)
                }


            }
        }

    }
}