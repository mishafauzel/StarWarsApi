package com.example.starwarsapi.sl.main

import com.example.starwarsapi.presentation.main.MainViewModel
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class MainModule(
    private val coreModule: CoreModule,
    private val mainNavigationSource: MainNavigationSource,
    private val progressCommunication: ProgressCommunication.Base
) : Module<MainViewModel> {

    override fun viewModel() =
        MainViewModel(
            coreModule.provideCanGoBack(),
            NavigationCommunication.Base(),
            progressCommunication,
            mainNavigationSource.provideNavigationComunication(),
            coreModule.dispatchers(),
            coreModule.provideGlobalErrorCommunication(),
        )
}
