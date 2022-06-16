package com.example.starwarsapi.sl

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.example.starwarsapi.sl.main.MainDataQueueSource
import com.example.starwarsapi.sl.main.MainNavigationSource
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class FeatureDependencyContainer(
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer,

    private val mainNavigationSource: MainNavigationSource,
    private val mainDataQueueSource: MainDataQueueSource
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> {
        return when (clazz) {
            PlanetsViewModel::class.java -> {
                return PlanetsAndCharactersModule(coreModule = coreModule,mainNavigationSource,mainDataQueueSource)
            }
            CharacterFullViewModel::class.java->
            {
                return CharacterFullInfoModule(coreModule,mainDataQueueSource)
            }
            else -> dependencyContainer.module(clazz)

        }
    }

}