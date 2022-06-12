package com.example.starwarsapi.sl

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.presentation.main.MainViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class MainDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule
):DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> {
        return if(clazz==MainViewModel::class.java)
            MainModule(coreModule)
        else
            dependencyContainer.module(clazz)
    }
}