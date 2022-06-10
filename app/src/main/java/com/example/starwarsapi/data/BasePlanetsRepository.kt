package com.example.starwarsapi.data

import com.example.starwarsapi.data.cache.PlanetCache
import com.example.starwarsapi.data.cache.PlanetCacheDataSource
import com.example.starwarsapi.data.cache.PlanetsCache
import com.example.starwarsapi.data.cloud.PlanetService
import com.example.starwarsapi.data.cloud.PlanetsCloud
import com.example.starwarsapi.domain.PagerDomain
import com.example.starwarsapi.domain.PlanetsDomain
import com.example.starwarsapi.domain.PlanetsRepository

class BasePlanetsRepository(
    private val planetCacheDataSource: PlanetCacheDataSource.Mutable,
    private val planetService: PlanetService,
    private val planetsCloudFactory: PlanetsCloud.Mapper.Factory<PlanetsCache>,
    private val planetCacheToListMapper: PlanetsCache.Mapper<List<PlanetCache>>,
    private val planetCacheToDomain: PlanetsCache.Mapper<PlanetsDomain>


) :
    PlanetsRepository {
    override suspend fun selectPlanetsByPage(page: Int): PlanetsDomain {
        try {
            val cache = planetCacheDataSource.read(page)
            if(cache.isEmpty())
            {
                val planetsCache=planetService.getPlanetsByPage(page).map(planetsCloudFactory.create(page))
                planetCacheDataSource.save(planetsCache.map(planetCacheToListMapper))
                return planetsCache.map(planetCacheToDomain)
            }
            return cache.map(planetCacheToDomain)


        } catch (ex: Exception) {
            return PlanetsDomain.Base(PagerDomain.Base("1", "2"), emptyList())
        }
    }
}
