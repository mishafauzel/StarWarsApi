package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.data.planets.BaseCharacterRepository
import com.example.starwarsapi.data.planets.BasePlanetsRepository
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.example.starwarsapi.domain.HandleDomainException
import com.example.starwarsapi.domain.planets.CharacterRepository
import com.example.starwarsapi.domain.planets.PlanetsRepository
import com.github.johnnysc.coremvvm.sl.CoreModule

interface DataDependenciesProvider {

    fun providePlanetRepository(): PlanetsRepository

    fun provideCharacterRepository(): CharacterRepository

    class Base(coreModule: CoreModule, private val provideServices: ProvideServices) :
        DataDependenciesProvider {
        private val appDatabase = coreModule.provideRoomDatabase(AbstractDatabase::class.java)
        private val handleException = HandleDomainException()

        override fun providePlanetRepository(): PlanetsRepository {
            val planetService = provideServices.providePlanetsService()
            return BasePlanetsRepository(
                PlanetCacheDataSource.Base(appDatabase.providePlanetsDao()),
                PlanetsCloudDataSource.Base(planetService, handleException),
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
                CharactersCacheDataSource.Base(appDatabase.provideCharactersDao())
            )
        }

        override fun provideCharacterRepository(): CharacterRepository {
            val characterService = provideServices.provideCharacterService()
            return BaseCharacterRepository(
                CharactersCacheDataSource.Base(appDatabase.provideCharactersDao()),
                CharacterCloudDataSource.Base(characterService, handleException),
                CharactersCloud.Mapper.Factory.Base(CharacterCloud.Mapper.Factory.Base(UrlIdMapper.IdConverter())),
                CharactersCache.Mapper.BaseToList(),
                CharactersCache.Mapper.BaseToListCharactersDomain(CharacterCache.Mapper.BaseToListOfCharacterDomain()),
                CharactersCache.Mapper.BaseToListOfIds(CharacterCache.Mapper.CharacterToIdMapper())
                )
        }

    }
}