package com.example.starwarsapi.sl

import android.util.Log
import com.example.starwarsapi.data.planets.BaseCharacterRepository
import com.example.starwarsapi.data.planets.BasePlanetsRepository
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetsCache
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.domain.planets.*
import com.example.starwarsapi.presentation.DataKeeper
import com.example.starwarsapi.presentation.main.GlobalNavigateCommunication
import com.example.starwarsapi.presentation.planets.ListMutator

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.presentation.HandleUiError
import com.github.johnnysc.coremvvm.sl.CoreModule
import java.security.PrivateKey

private const val TAG = "PlanetDependencyProvide"
interface PlanetDependencyProvider {
    fun provideCharacterRepository(): CharacterRepository
    fun providePlanetsRepository(): PlanetsRepository
    fun providePlanetInteractor(
        characterRepository: CharacterRepository,
        planetsRepository: PlanetsRepository,
        navigationCommunication: GlobalNavigateCommunication.Update,
        handleError: HandleError,
        dataKeeper: DataQueue.Base
    ): PlanetsInteractor

    class Base(
        private val provideServices: ProvideServices,
        private val roomDatabase: AbstractDatabase,
        private val core: CoreModule,
         private val listMutator: ListMutator
    ) : PlanetDependencyProvider {
        private val urlIdMapper = UrlIdMapper.IdConverter()
        val charactersCacheDataSource =
            CharactersCacheDataSource.Base(roomDatabase.provideCharactersDao())

        override fun provideCharacterRepository(): CharacterRepository {

            val characterService = provideServices.provideCharacterService()
            return BaseCharacterRepository(
                charactersCacheDataSource,
                characterService,
                CharactersCloud.Mapper.Factory.Base(CharacterCloud.Mapper.Factory.Base(urlIdMapper)),
                CharactersCache.Mapper.BaseToList(),
                CharactersCache.Mapper.BaseToListCharactersDomain(CharacterCache.Mapper.BaseToListOfCharacterDomain()),
                CharactersCache.Mapper.BaseToListOfIds(
                    CharacterCache.Mapper.CharacterToIdMapper(urlIdMapper)
                )
            )
        }

        override fun providePlanetsRepository(): PlanetsRepository {
            val planetCacheDataSource = PlanetCacheDataSource.Base(roomDatabase.providePlanetsDao())
            val planetService = provideServices.providePlanetsService()

            val urlIdMapper = UrlIdMapper.IdConverter()
            return BasePlanetsRepository(
                planetCacheDataSource,
                planetService,
                PlanetsCloud.Mapper.Factory.Base(PlanetCloud.Mapper.Factory.Base(urlIdMapper)),
                PlanetsCache.Mapper.BaseToList(),
                PlanetsCache.Mapper.BaseToDomain(
                    PlanetCache.Mapper.BaseToPlanetDomain(),
                    PlanetCache.Mapper.BaseToPagerDomain(urlIdMapper)
                ),
                PlanetsCloud.Mapper.BaseToListCharacters(
                    PlanetCloud.Mapper.MapperToListOfCharacters(
                        urlIdMapper
                    )
                ),
                charactersCacheDataSource,
            )
        }

        override fun providePlanetInteractor(
            characterRepository: CharacterRepository,
            planetsRepository: PlanetsRepository,
            navigationCommunication: GlobalNavigateCommunication.Update,
            handleError: HandleError,
            dataKeeper: DataQueue.Base
        ): PlanetsInteractor {
            Log.d(TAG, "providePlanetInteractor: ${navigationCommunication.hashCode()}")
            return PlanetsInteractor.Base(
                PlanetsDomain.Mapper.BaseToUI(
                    PlanetDomain.Mapper.BaseToUI(
                        CharacterDomain.Mapper.Base(navigationCommunication, dataQueue = dataKeeper ),listMutator
                    ), PagerDomain.Mapper.Base()
                ),
                planetsRepository,
                PlanetsDomain.Mapper.BaseToWithResidence(
                    PlanetDomain.Mapper.BaseToPlanetWithResidence(characterRepository)
                ),
                core.dispatchers(), handleError
            )
        }
    }
}