package com.example.starwarsapi.sl

import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.presentation.planets.*
import com.example.starwarsapi.presentation.planets.base_communications.*
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.sl.main.MainDataQueueSource
import com.example.starwarsapi.sl.main.MainNavigationSource
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class PlanetsAndCharactersModule(
    private val coreModule: CoreModule,
    private val mainNavigationSource: MainNavigationSource,
    private val mainDataQueueSource: MainDataQueueSource
) :
    Module<PlanetsViewModel> {

    override fun viewModel(): PlanetsViewModel {
        val listMutator = ListMutator(PlanetsUi.Mapper.Base())

        val roomDatabase = coreModule.provideRoomDatabase(AbstractDatabase::class.java)
        val provideServices = ProvideServices.Base(coreModule)

        val nextPageCommunication = NextPageCommunication.Base()
        val planetDependencyProvider =
            PlanetDependencyProvider.Base(
                provideServices,
                roomDatabase,
                coreModule,
                listMutator,
                nextPageCommunication
            )


        val errorComunication = PlanetsErrorComunication.Base()

        val planetErrorHandle = PlanetsErrorHandle(errorComunication)
        return PlanetsViewModel(
            coreModule.provideCanGoBack(),
            planetDependencyProvider.providePlanetInteractor(
                planetDependencyProvider.provideCharacterRepository(),
                planetDependencyProvider.providePlanetsRepository(),
                mainNavigationSource.provideNavigationComunication(),
                planetErrorHandle,
                mainDataQueueSource.provideDataQueue()
            ),
            errorComunication,
            coreModule.provideProgressCommunication(),
            nextPageCommunication,
            listMutator,
            PagerData.Mapper.Base(),
            PlanetsCommunication.Base(),
            coreModule.dispatchers()
        )

    }

}