package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.data.planets.BaseCharacterRepository
import com.example.starwarsapi.data.planets.BasePlanetsRepository
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.example.starwarsapi.domain.planets.CharacterRepository
import com.example.starwarsapi.domain.planets.PlanetsRepository
import com.example.starwarsapi.sl.DataSourceProvider

interface DataDependenciesProvider {

    fun providePlanetRepository(): PlanetsRepository

    fun provideCharacterRepository(): CharacterRepository

    class Base(
        private val planetsLocalDataSourceProvider: DataSourceProvider<PlanetCacheDataSource.Mutable>,
        private val planetsRemoteDataSourceProvider: DataSourceProvider<PlanetsCloudDataSource>,
        private val characterLocalDataSourceProvider: DataSourceProvider<CharactersCacheDataSource.Mutable>,
        private val characterRemoteDataSourceProvider: DataSourceProvider<CharacterCloudDataSource>
    ) :
        DataDependenciesProvider {




        override fun providePlanetRepository(): PlanetsRepository {

            return BasePlanetsRepository(
                planetsLocalDataSourceProvider.provideDataSource(),
                planetsRemoteDataSourceProvider.provideDataSource(),
                PlanetsCloud.Mapper.Factory.Base(
                    PlanetCloud.Mapper.Factory.Base(
                        UrlIdMapper.PageConverter(),
                        UrlIdMapper.IdConverter()
                    )
                ),
                PlanetsCache.Mapper.BaseToList(),
                PlanetsCache.Mapper.BaseToDomain(
                    PlanetCache.Mapper.BaseToPlanetDomain(),
                    PlanetCache.Mapper.BaseToPagerDomain()
                ),
                PlanetsCloud.Mapper.BaseToListCharacters(
                    PlanetCloud.Mapper.MapperToListOfCharacters(
                        UrlIdMapper.IdConverter()
                    )
                ),
                characterLocalDataSourceProvider.provideDataSource()
            )
        }

        override fun provideCharacterRepository(): CharacterRepository {

            return BaseCharacterRepository(
                characterLocalDataSourceProvider.provideDataSource(),
                characterRemoteDataSourceProvider.provideDataSource(),
                CharactersCloud.Mapper.Factory.Base(CharacterCloud.Mapper.Factory.Base(UrlIdMapper.IdConverter())),
                CharactersCache.Mapper.BaseToList(),
                CharactersCache.Mapper.BaseToListCharactersDomain(CharacterCache.Mapper.BaseToListOfCharacterDomain()),
                CharactersCache.Mapper.BaseToListOfIds(CharacterCache.Mapper.CharacterToIdMapper())
            )
        }

    }
}