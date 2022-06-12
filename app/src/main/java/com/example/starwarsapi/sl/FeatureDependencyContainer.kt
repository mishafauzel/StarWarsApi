package com.example.starwarsapi.sl

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.presentation.planets.PlanetsViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class FeatureDependencyContainer(
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer,private val dataModule: DataModule
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> {
        return when (clazz) {
            PlanetsViewModel::class.java -> {
                return PlanetsAndCharactersModule(coreModule = coreModule,dataModule)
            }
            CharacterFullViewModel::class.java->
            {
                return CharacterFullInfoModule(coreModule,dataModule)
            }
            else -> dependencyContainer.module(clazz)

        }
    }

}