package com.example.starwarsapi.sl.base

import com.github.johnnysc.coremvvm.presentation.NavigationCommunication


class MainNavigationSource {

    private val globalNavigationCommunication =NavigationCommunication.Base()

    fun provideNavigationComunication(): NavigationCommunication.Base =
        globalNavigationCommunication

}
