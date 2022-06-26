package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.domain.planets.*
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.LiveDataTransformator
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError

interface DomainDependenciesProvider {

    fun providePlanetsInteractor(
        listMutator: LiveDataTransformator<PlanetsUi, PlanetsUi>,
        ): PlanetsInteractor

    class Base(
        private val mainDataQueueSource: MainDataQueueSource,
        private val mainNavigationSource: MainNavigationSource,
        private val dataDependenciesProvider: DataDependenciesProvider,
        private val errorCommunication: HandleError,
        private val dispatchers: Dispatchers,
        private val getInfoCommunication: GetInfoCommunication
    ) : DomainDependenciesProvider {

        override fun providePlanetsInteractor(
            listMutator: LiveDataTransformator<PlanetsUi, PlanetsUi>
            ): PlanetsInteractor {
            val mainDataQueue = mainDataQueueSource.provideDataQueue()
            val mainNavigation = mainNavigationSource.provideNavigationComunication()


            return PlanetsInteractor.Base(
                PlanetsDomain.Mapper.BaseToUI(
                    PlanetDomain.Mapper.BaseToUI(
                        CharacterDomain.Mapper.Base(mainNavigation, mainDataQueue),
                        listMutator
                    ), PagerDomain.Mapper.Base(getInfoCommunication)
                ),
                dataDependenciesProvider.providePlanetRepository(),
                PlanetsDomain.Mapper.BaseToWithResidence(
                    PlanetDomain.Mapper.BaseToPlanetWithResidence(dataDependenciesProvider.provideCharacterRepository())
                ), PagerDomain.Mapper.BaseToInt(),
                PlanetsDomain.Mapper.BaseToPagerDomain(),
                dispatchers,
                errorCommunication

            )
        }
    }
}