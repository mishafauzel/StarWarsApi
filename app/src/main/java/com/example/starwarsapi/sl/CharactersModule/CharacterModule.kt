package com.example.starwarsapi.sl.CharactersModule

import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.presentation.character.base_communications.CharacterFullCommunication
import com.example.starwarsapi.presentation.character.base_communications.RetryCommunication
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class CharacterModule(
    private val coreModule: CoreModule,
    private val provideServices: ProvideServices,
    private val manageResources: ManageResources,
    private val progressCommunication: ProgressCommunication.Base,
    private val dataQueueSource: MainDataQueueSource
) : Module<CharacterFullViewModel> {

    override fun viewModel(): CharacterFullViewModel {
        val retryCommunication=RetryCommunication.Base()
        val dataDependeciesProvider = DataDependeciesProvider.Base(coreModule, provideServices)
        val domainDependensiesProvider = DomainDependensiesProvider.Base(
            dataDependeciesProvider,
            coreModule,
            manageResources,
            retryCommunication
        )
        return CharacterFullViewModel(
            coreModule.provideCanGoBack(),
            domainDependensiesProvider.provideCharacterInteractor(),
            progressCommunication,
            retryCommunication,
            CharacterFullCommunication.Base(),
            coreModule.dispatchers(),
            dataQueueSource.provideDataQueue().read() as Int
        )
    }
}