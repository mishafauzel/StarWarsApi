package com.example.starwarsapi.data.planets.cache.planets

interface PlanetCacheDataSource {
    interface Save : com.github.johnnysc.coremvvm.core.Save<List<PlanetCache>>

    interface Read : com.example.starwarsapi.core.Read<Int, PlanetsCache>

    interface Mutable : Save, Read

    class Base(private val planetsDao: PlanetsDao) : Mutable {
        override fun save(data: List<PlanetCache>) {

            planetsDao.save(data.map {
                it as PlanetCache.Base
            })//тут ннемного схитрить пришлось
        }

        override fun read(inputData: Int): PlanetsCache {
            return PlanetsCache.Base(planetsDao.selectByPage(inputData))
        }
    }
}