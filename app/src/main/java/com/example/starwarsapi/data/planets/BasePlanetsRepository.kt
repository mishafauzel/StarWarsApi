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
    private val planetService: PlanetsCloudDataSource,
    private val factoryMapperPlanetsCloudToPlanetsCashe: PlanetsCloud.Mapper.Factory<PlanetsCache>,
    private val mapperPlanetsCacheToListPlanetCache: PlanetsCache.Mapper<List<PlanetCache>>,
    private val mapperPlanCahceToPlanetsDomain: PlanetsCache.Mapper<PlanetsDomain>,
    private val mapperPlanetsCloudToListChararacterCache: PlanetsCloud.Mapper<List<CharacterCache>>,
    private val charactersCacheDataSource: CharactersCacheDataSource.Save
) : PlanetsRepository {

    override suspend fun selectPlanetsByPage(page: Int): PlanetsDomain {
        val result: PlanetsDomain
        val cache = planetCacheDataSource.read(page)
        if (cache.isEmpty()) {
            val planetsCloud = planetService.planetsByPage(page)
            val planetsCache =
                planetsCloud.map(factoryMapperPlanetsCloudToPlanetsCashe.create(page))
            val charactersCache = planetsCloud.map(mapperPlanetsCloudToListChararacterCache)
            planetCacheDataSource.save(planetsCache.map(mapperPlanetsCacheToListPlanetCache))
            charactersCacheDataSource.save(charactersCache)
            result = planetsCache.map(mapperPlanCahceToPlanetsDomain)
        } else
            result = cache.map(mapperPlanCahceToPlanetsDomain)
        return result
    }
}
