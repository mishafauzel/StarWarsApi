package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.domain.planets.*
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.sl.CoreModule

interface DomainDependensiesProvider {

    fun providePlanetsInteractor(
        listMutator: ListMutator,
        nextPageCommunication: NextPageCommunication.Update,
        retryCommunication: RetryCommunication.Update
    ): PlanetsInteractor

    class Base(
        private val mainDataQueueSource: MainDataQueueSource,
        private val mainNavigationSource: MainNavigationSource,
        private val dataDependenciesProvider: DataDependenciesProvider,
        private val manageResources: ManageResources,
        private val coreModule: CoreModule
    ) : DomainDependensiesProvider {

        override fun providePlanetsInteractor(
            listMutator: ListMutator,
            nextPageCommunication: NextPageCommunication.Update,
            retryCommunication: RetryCommunication.Update
        ): PlanetsInteractor {
            val mainDataQueue = mainDataQueueSource.provideDataQueue()
            val mainNavigation = mainNavigationSource.provideNavigationComunication()


            return PlanetsInteractor.Base(
                PlanetsDomain.Mapper.BaseToUI(
                    PlanetDomain.Mapper.BaseToUI(
                        CharacterDomain.Mapper.Base(mainNavigation, mainDataQueue),
                        listMutator
                    ), PagerDomain.Mapper.Base(nextPageCommunication)
                ),
                dataDependenciesProvider.providePlanetRepository(),
                PlanetsDomain.Mapper.BaseToWithResidence(
                    PlanetDomain.Mapper.BaseToPlanetWithResidence(dataDependenciesProvider.provideCharacterRepository())
                ),PagerData.Mapper.Base(),DomainException.Mapper.Factory.Base(manageResources,retryCommunication),
                coreModule.dispatchers()
            )
        }
    }
}