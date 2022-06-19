package com.example.starwarsapi.sl.CharactersModule

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer

class CharactersDependensiesContainer(
    private val dependencyContainer: DependencyContainer,
    coreModule: CoreModule,
    provideServices: ProvideServices,
    manageResources: ManageResources,
    progressCommunication: ProgressCommunication.Base,
    mainDataQueueSource: MainDataQueueSource
) : DependencyContainer {

    private val characterModule: CharacterModule

    init {
        characterModule = CharacterModule(
            coreModule,
            provideServices,
            manageResources,
            progressCommunication,
            mainDataQueueSource
        )
    }

    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == CharacterFullViewModel::class.java)
            characterModule
        else
            dependencyContainer.module(clazz)
}