package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.LiveDataTransformator
import com.example.starwarsapi.presentation.planets.base_communications.ErrorCommunication
import com.example.starwarsapi.presentation.main.UnsupportedErrorCommunication
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.sl.DataSourceProvider
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.Module
import java.security.PrivateKey

class PlanetsAndCharactersModule(
    private val canGoBack: CanGoBack.Callback,
    private val mainDataQueueSource: MainDataQueueSource,
    private val navigationCommunicationSource: MainNavigationSource,
    private val progressCommunication: ProgressCommunication.Base,
    private val dispatchers: Dispatchers,
    private val manageResources: ManageResources,
    private val planetsCacheSourceProvider: DataSourceProvider<PlanetCacheDataSource.Mutable>,
    private val planetsRemoteSourceProvider:DataSourceProvider<PlanetsCloudDataSource>,
    private val characterCacheSourceProvider:DataSourceProvider<CharactersCacheDataSource.Mutable>,
    private val characterRemoteSourceProvider:DataSourceProvider<CharacterCloudDataSource>,
    private val unsupportedErrorCommunication: UnsupportedErrorCommunication,
    private val planetsCommunication:PlanetsCommunication=PlanetsCommunication.Base(),
    private val listMutator:LiveDataTransformator<PlanetsUi, PlanetsUi> = ListMutator(PlanetsUi.Mapper.Base())
) : Module<PlanetsViewModel> {

    override fun viewModel(): PlanetsViewModel {
        val getInfoCommunication=GetInfoCommunication.Base()


        val errorCommunication= ErrorCommunication(unsupportedErrorCommunication,planetsCommunication,
            DomainException.Mapper.Factory.Base(manageResources,getInfoCommunication))
        val dataDependenciesProvider=DataDependenciesProvider.Base(planetsCacheSourceProvider, planetsRemoteSourceProvider, characterCacheSourceProvider,characterRemoteSourceProvider)
        val domainDependenciesProvider=DomainDependenciesProvider.Base(mainDataQueueSource,navigationCommunicationSource,dataDependenciesProvider,errorCommunication,dispatchers,getInfoCommunication)
        return PlanetsViewModel(
            canGoBack,
            domainDependenciesProvider.providePlanetsInteractor(listMutator),
            progressCommunication,
            listMutator,
            planetsCommunication,
            dispatchers, getInfoCommunication
        )
    }

}