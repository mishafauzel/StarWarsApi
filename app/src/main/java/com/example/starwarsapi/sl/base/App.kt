package com.example.starwarsapi.sl.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.starwarsapi.sl.CharactersModule.CharactersDependensiesContainer
import com.example.starwarsapi.sl.PlanetsModule.PlanetsDependensiesContainer
import com.example.starwarsapi.sl.main.MainDependencyContainer
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.coremvvm.sl.ViewModelsFactory

class App : Application(), ProvideViewModel {
    private lateinit var viewModelsFactory: ViewModelsFactory
    private lateinit var mainNavigationSource: MainNavigationSource
    private lateinit var mainDataQueueSource: MainDataQueueSource

    override fun onCreate() {
        super.onCreate()
        mainDataQueueSource = MainDataQueueSource()
        mainNavigationSource = MainNavigationSource()
        val mangeResourceProvider = MangeResourceProvider.Base()
        val manageResouce = mangeResourceProvider.provideManageResource(this)
        val coreModule = CoreModule.Base(this)
        val progressComunication = coreModule.provideProgressCommunication()
        val provideServices = ServiceProviderSource.Base(coreModule).provideServices()
        val main =
            MainDependencyContainer(
                DependencyContainer.Error(),
                coreModule,
                mainNavigationSource,
                progressComunication
            )

        viewModelsFactory = ViewModelsFactory(
            CharactersDependensiesContainer(
                PlanetsDependensiesContainer(
                    main, coreModule, mainNavigationSource, mainDataQueueSource,
                    manageResouce, provideServices, progressComunication
                ),
                coreModule,
                provideServices,
                manageResouce,
                progressComunication,
                mainDataQueueSource
            )
        )

    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T {
        return ViewModelProvider(owner, viewModelsFactory)[clazz]
    }

}