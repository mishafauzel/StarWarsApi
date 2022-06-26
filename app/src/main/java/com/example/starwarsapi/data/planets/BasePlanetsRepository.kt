package com.example.starwarsapi.data.planets

import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.example.starwarsapi.domain.planets.PlanetsDomain
import com.example.starwarsapi.domain.planets.PlanetsRepository


class BasePlanetsRepository(
    private val planetCacheDataSource: PlanetCacheDataSource.Mutable,
    private val planetCloudDataSource: PlanetsCloudDataSource,
    private val factoryMapperPlanetsCloudToPlanetsCache: PlanetsCloud.Mapper.Factory<PlanetsCache>,
    private val mapperPlanetsCacheToListPlanetCache: PlanetsCache.Mapper<List<PlanetCache>>,
    private val mapperPlanCacheToPlanetsDomain: PlanetsCache.Mapper<PlanetsDomain>,
    private val mapperPlanetsCloudToListCharacterCache: PlanetsCloud.Mapper<List<CharacterCache>>,
    private val charactersCacheDataSource: CharactersCacheDataSource.Save
) : PlanetsRepository {

    override suspend fun selectPlanetsByPage(page: Int): PlanetsDomain {
        val cache = planetCacheDataSource.read(page)
         return if (cache.isEmpty()) {
            val planetsCloud = planetCloudDataSource.planetsByPage(page)
            val planetsCache =
                planetsCloud.map(factoryMapperPlanetsCloudToPlanetsCache.create(page))
            val charactersCache = planetsCloud.map(mapperPlanetsCloudToListCharacterCache)
            planetCacheDataSource.save(planetsCache.map(mapperPlanetsCacheToListPlanetCache))
            charactersCacheDataSource.save(charactersCache)
            planetsCache.map(mapperPlanCacheToPlanetsDomain)
        } else
            cache.map(mapperPlanCacheToPlanetsDomain)

    }
}
