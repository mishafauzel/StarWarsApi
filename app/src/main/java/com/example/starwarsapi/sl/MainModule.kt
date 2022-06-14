package com.example.starwarsapi.sl

import android.util.Log
import com.example.starwarsapi.presentation.main.GlobalNavigateCommunication
import com.example.starwarsapi.presentation.main.MainViewModel
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

private const val TAG = "MainModule"
class MainModule(private val coreModule: CoreModule,private val mainNavigationSource: MainNavigationSource):Module<MainViewModel> {


    override fun viewModel(): MainViewModel {

        return MainViewModel(
            coreModule.provideCanGoBack(),
            NavigationCommunication.Base(),
            coreModule.provideProgressCommunication(),
            mainNavigationSource.provideNavigationComunication(),
            coreModule.dispatchers(),
            coreModule.provideGlobalErrorCommunication()
        )
    }


}