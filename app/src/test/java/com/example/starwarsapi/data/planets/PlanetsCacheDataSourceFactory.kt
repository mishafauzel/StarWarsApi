//package com.example.starwarsapi.data.planets
//
//import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
//import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
//import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
//import com.example.starwarsapi.data.planets.cache.planets.TestPlanetsCacheDataSource
//
//class PlanetsCacheDataSourceFactory {
//
//    fun createCacheDataSource(): TestPlanetsCacheDataSource {
//        return TestPlanetsCacheDataSource(mutableMapOf(), PlanetCache.Mapper.BaseToCurPage())
//    }
//}