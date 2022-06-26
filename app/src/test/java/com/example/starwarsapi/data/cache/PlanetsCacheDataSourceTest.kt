package com.example.starwarsapi.data.cache

import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource

class PlanetsCacheDataSourceTest( isAvailable: Boolean) : PlanetCacheDataSource.Mutable {
    private val mutableMap = mutableMapOf<Int, List<PlanetCache>>()

    init {
        if (isAvailable) {
            val firstPageList = listOf<PlanetCache>(
                PlanetCache.Base(1, "1", 1, 2),
                PlanetCache.Base(2, "2", 1, 2)
            )
            mutableMap[1] = firstPageList
            val secondPageList = listOf<PlanetCache>(
                PlanetCache.Base(3, "3", 2, -1),
                PlanetCache.Base(4, "4", 2, -1)
            )
            mutableMap[2] = secondPageList
        }
    }

    override fun save(data: List<PlanetCache>) {
        val keys=mutableMap.keys
        if(keys.size==0)
        {
            mutableMap[1]=data
        }
        else
        {
            val lastKey=keys.last()
            mutableMap[lastKey+1]=data
        }
    }

    override fun read(inputData: Int): PlanetsCache {
        val result = mutableMap[inputData]
        if (result == null) {
            return PlanetsCache.Base(emptyList())
        } else
            return PlanetsCache.Base(result)

    }

}