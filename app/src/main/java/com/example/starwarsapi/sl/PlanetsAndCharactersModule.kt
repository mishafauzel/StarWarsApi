package com.example.starwarsapi.sl

import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.presentation.planets.*
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class PlanetsAndCharactersModule(private val coreModule: CoreModule,private val mainNavigationSource: MainNavigationSource,private val mainDataQueueSource: MainDataQueueSource) :
    Module<PlanetsViewModel> {

    override fun viewModel(): PlanetsViewModel {
        val listMutator=ListMutator(PlanetsUi.Mapper.Base())

        val roomDatabase = coreModule.provideRoomDatabase(AbstractDatabase::class.java)
        val provideServices = ProvideServices.Base(coreModule)
        val planetDependencyProvider =
            PlanetDependencyProvider.Base(provideServices, roomDatabase, coreModule,listMutator)



        val errorComunication=PlanetsErrorComunication.Base()

        val planetErrorHandle=PlanetsErrorHandle(errorComunication)
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
            listMutator,
            PlanetsCommunication.Base(),
            coreModule.dispatchers()
        )

    }

}