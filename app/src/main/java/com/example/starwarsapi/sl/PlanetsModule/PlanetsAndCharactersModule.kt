package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class PlanetsAndCharactersModule(
    private val coreModule: CoreModule,
    private val mainNavigationSource: MainNavigationSource,
    private val mainDataQueueSource: MainDataQueueSource,
    private val manageResources: ManageResources,
    private val provideServices: ProvideServices,
    private val progressCommunication: ProgressCommunication.Base
) : Module<PlanetsViewModel> {

    override fun viewModel(): PlanetsViewModel {
        val dataDependensiesProvider = DataDependenciesProvider.Base(coreModule, provideServices)
        val domainDependensiesProvider = DomainDependensiesProvider.Base(
            mainDataQueueSource,
            mainNavigationSource,
            dataDependensiesProvider,
            manageResources,
            coreModule
        )
        val listMutator = ListMutator(PlanetsUi.Mapper.Base())
        val nextPageCommunication = NextPageCommunication.Base()
        val retryCommunication = RetryCommunication.Base()
        return PlanetsViewModel(
            coreModule.provideCanGoBack(),
            domainDependensiesProvider.providePlanetsInteractor(
                listMutator,
                nextPageCommunication,
                retryCommunication
            ),
            progressCommunication,
            nextPageCommunication,
            retryCommunication,
            listMutator,
            PlanetsCommunication.Base(),
            coreModule.dispatchers()
        )
    }

}