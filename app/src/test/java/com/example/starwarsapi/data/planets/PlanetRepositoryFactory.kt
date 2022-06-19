//package com.example.starwarsapi.data.planets
//
//import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
//import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
//import com.example.starwarsapi.data.planets.cache.planets.TestPlanetsCacheDataSource
//import com.example.starwarsapi.data.planets.cloud.planets.PlanetCloud
//import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
//import com.example.starwarsapi.data.planets.cloud.planets.TestPlanetCloudDataSource
//import com.example.starwarsapi.domain.planets.PlanetsRepository
//
//class PlanetRepositoryFactory {
//    fun create(cloudState:TestPlanetCloudDataSource.State):PlanetsRepository
//    {
//        val planetsCacheDataSourceFactory=PlanetsCacheDataSourceFactory()
//        val planetCloudDataSourceFactory=PlanetCloudDataSourceFactory()
//        return BasePlanetsRepository(
//            planetsCacheDataSourceFactory.createCacheDataSource(),
//            planetCloudDataSourceFactory.createPlanetsCloudDataSource(cloudState),
//            PlanetsCloud.Mapper.Factory.Base(PlanetCloud.Mapper.Factory.Base(UrlIdMapper.PageConverter(),UrlIdMapper.IdConverter())),
//            PlanetsCache.Mapper.BaseToList(),
//            PlanetsCache.Mapper.BaseToDomain(PlanetCache.Mapper.BaseToPlanetDomain(),PlanetCache.Mapper.BaseToPagerDomain()),
//
//
//
//        )
//    }
//}