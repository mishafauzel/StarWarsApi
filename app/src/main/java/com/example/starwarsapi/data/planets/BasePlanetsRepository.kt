package com.example.starwarsapi.data.planets

import android.util.Log
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.example.starwarsapi.data.planets.cloud.planets.PlanetService
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.domain.planets.PagerDomain
import com.example.starwarsapi.domain.planets.PlanetsDomain
import com.example.starwarsapi.domain.planets.PlanetsRepository

class BasePlanetsRepository(
    private val planetCacheDataSource: PlanetCacheDataSource.Mutable,
    private val planetService: PlanetService,
    private val planetsCloudToPlanetCacheMapperFactory: PlanetsCloud.Mapper.Factory<PlanetsCache>,
    private val planetCacheToListMapper: PlanetsCache.Mapper<List<PlanetCache>>,
    private val planetCacheToDomain: PlanetsCache.Mapper<PlanetsDomain>,
    private val planetsCloudToCharacterCache: PlanetsCloud.Mapper<List<CharacterCache>>,
    private val charactersCacheDataSource: CharactersCacheDataSource.Save


) :
    PlanetsRepository {
    override suspend fun selectPlanetsByPage(page: Int): PlanetsDomain {

            val cache = planetCacheDataSource.read(page)
            if(cache.isEmpty())
            {
                val planetsCloud=planetService.getPlanetsByPage(page)
                val planetsCache=planetsCloud.map(planetsCloudToPlanetCacheMapperFactory.create(page))
                val charactersCache=planetsCloud.map(planetsCloudToCharacterCache)
                planetCacheDataSource.save(planetsCache.map(planetCacheToListMapper))
                charactersCacheDataSource.save(charactersCache)

                return planetsCache.map(planetCacheToDomain)
            }
            return cache.map(planetCacheToDomain)



    }
}
