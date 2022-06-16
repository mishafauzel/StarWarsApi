package com.example.starwarsapi.sl.main

import com.example.starwarsapi.presentation.main.GlobalNavigateCommunication

class MainNavigationSource {
    private val globalNavigationCommunication = GlobalNavigateCommunication.Base()
    fun provideNavigationComunication(): GlobalNavigateCommunication.Base =
        globalNavigationCommunication
}