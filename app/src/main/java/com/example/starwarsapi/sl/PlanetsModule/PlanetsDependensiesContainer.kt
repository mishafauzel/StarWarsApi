package com.example.starwarsapi.sl.PlanetsModule

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer

class PlanetsDependensiesContainer(
    private val dependencyContainer: DependencyContainer,
    coreModule: CoreModule,
    mainNavigationSource: MainNavigationSource,
    mainDataQueueSource: MainDataQueueSource,
    manageResources: ManageResources,
    provideServices: ProvideServices,
    progressCommunication: ProgressCommunication.Base
) : DependencyContainer {

    private val planetsAndCharactersModule: PlanetsAndCharactersModule

    init {
        planetsAndCharactersModule = PlanetsAndCharactersModule(
            coreModule,
            mainNavigationSource,
            mainDataQueueSource,
            manageResources,
            provideServices,
            progressCommunication
        )
    }

    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == PlanetsViewModel::class.java) {
            planetsAndCharactersModule
        } else {
            dependencyContainer.module(clazz)
        }
}
