package com.example.starwarsapi.sl.PlanetsModule

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.presentation.main.UnsupportedErrorCommunication
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.example.starwarsapi.presentation.planets.base_communications.ErrorCommunication
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.sl.DataSourceProvider
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.DependencyContainer

class PlanetsDependenciesContainer(
    private val dependencyContainer: DependencyContainer,
    canGoBack: CanGoBack.Callback,
    mainNavigationSource: MainNavigationSource,
    mainDataQueueSource: MainDataQueueSource,
    manageResources: ManageResources,
    progressCommunication: ProgressCommunication.Base,
    unsupportedErrorCommunication: UnsupportedErrorCommunication,
    planetsLocalDataSourceProvider: DataSourceProvider<PlanetCacheDataSource.Mutable>,
    planetsRemoteDataSourceProvider: DataSourceProvider<PlanetsCloudDataSource>,
    charactersLocalDataSourceProvider: DataSourceProvider<CharactersCacheDataSource.Mutable>,
    characterRemoteDataSourceProvider: DataSourceProvider<CharacterCloudDataSource>,
    dispatchers: Dispatchers
) : DependencyContainer {

    private val planetsAndCharactersModule: PlanetsAndCharactersModule

    init {
        planetsAndCharactersModule = PlanetsAndCharactersModule(
            canGoBack,
            mainDataQueueSource,
            mainNavigationSource,
            progressCommunication,
            dispatchers,
            manageResources,
            planetsLocalDataSourceProvider,
            planetsRemoteDataSourceProvider,
            charactersLocalDataSourceProvider,
            characterRemoteDataSourceProvider,
            unsupportedErrorCommunication
        )
    }

    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == PlanetsViewModel::class.java) {
            planetsAndCharactersModule
        } else {
            dependencyContainer.module(clazz)
        }
}
