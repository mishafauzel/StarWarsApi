package com.example.starwarsapi.sl.base

import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.github.johnnysc.coremvvm.sl.CoreModule

interface ServiceProviderSource {

    fun provideServices():ProvideServices

    class Base(private val coreModule: CoreModule):ServiceProviderSource {

        override fun provideServices(): ProvideServices {
            return ProvideServices.Base(coreModule)
        }
    }
}