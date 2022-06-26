package com.example.starwarsapi.sl.main

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.presentation.main.MainViewModel
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer

class MainDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule,
    private val mainNavigationSource: MainNavigationSource,
    private val progressCommunication: ProgressCommunication.Base
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == MainViewModel::class.java) {
            MainModule(coreModule, mainNavigationSource.provideNavigationComunication(), progressCommunication)
        } else
            dependencyContainer.module(clazz)

}
