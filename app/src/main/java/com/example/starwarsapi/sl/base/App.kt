package com.example.starwarsapi.sl.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.domain.HandleDomainException
import com.example.starwarsapi.presentation.main.UnsupportedErrorCommunication
import com.example.starwarsapi.sl.CharactersModule.CharactersDependenciesContainer
import com.example.starwarsapi.sl.PlanetsModule.*
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
        val handleDomainException = HandleDomainException()
        val dispatchers = coreModule.dispatchers()
        val unsupportedErrorCommunication = UnsupportedErrorCommunication(
            coreModule.provideGlobalErrorCommunication(),
            manageResouce
        )
        val provideServices = ServiceProviderSource.Base(coreModule).provideServices()
        val main =
            MainDependencyContainer(
                DependencyContainer.Error(),
                coreModule,
                mainNavigationSource,
                progressComunication
            )

        viewModelsFactory = ViewModelsFactory(
            CharactersDependenciesContainer(
                PlanetsDependenciesContainer(
                    main,
                    coreModule.provideCanGoBack(),
                    mainNavigationSource,
                    mainDataQueueSource,
                    manageResouce,
                    progressComunication,
                    unsupportedErrorCommunication,
                    BasePlanetsLocal(coreModule),
                    BasePlanetsRemote(provideServices, handleDomainException),
                    BaseCharacterLocal(coreModule),
                    BaseCharacterRemote(provideServices, handleDomainException), dispatchers
                ),
               dispatchers,coreModule.provideCanGoBack(),unsupportedErrorCommunication,manageResouce,progressComunication,mainDataQueueSource,BaseCharacterFullInfoCacheDataSourceProvide(coreModule),BaseCharacterFullInfoRemoteSource(provideServices,handleDomainException)
            )
        )

    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T {
        return ViewModelProvider(owner, viewModelsFactory)[clazz]
    }

}