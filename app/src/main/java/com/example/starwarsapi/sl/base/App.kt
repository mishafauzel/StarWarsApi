package com.example.starwarsapi.sl.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.starwarsapi.sl.FeatureDependencyContainer
import com.example.starwarsapi.sl.main.MainDependencyContainer
import com.example.starwarsapi.sl.main.MainDataQueueSource
import com.example.starwarsapi.sl.main.MainModule
import com.example.starwarsapi.sl.main.MainNavigationSource
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.coremvvm.sl.ViewModelsFactory

class App:Application(),ProvideViewModel {
    private lateinit var viewModelsFactory: ViewModelsFactory
    private lateinit var mainNavigationSource: MainNavigationSource
    private lateinit var mainDataQueueSource: MainDataQueueSource
    override fun onCreate() {
        super.onCreate()
        mainDataQueueSource= MainDataQueueSource()
        mainNavigationSource= MainNavigationSource()
        val coreModule = CoreModule.Base(this)
        val main=
            MainDependencyContainer(DependencyContainer.Error(),coreModule,mainNavigationSource)
        val mainModule= MainModule(coreModule,mainNavigationSource)
        viewModelsFactory=ViewModelsFactory(FeatureDependencyContainer(coreModule,main,mainNavigationSource,mainDataQueueSource))

    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T {
        return ViewModelProvider(owner,viewModelsFactory)[clazz]
    }
}