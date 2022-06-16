package com.example.starwarsapi.presentation.main

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.presentation.character.nav_screen.CharacterFullInfoScreen
import com.example.starwarsapi.presentation.planets.nav_screen.PlanetNavigationScreen
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*


class MainViewModel(
    canGoBack: CanGoBack,
    private val navigationCommunication: NavigationCommunication.Mutable,
    private val progressCommunication: ProgressCommunication.Mutable,
    private val globalNavigateCommunication: GlobalNavigateCommunication.Observe,
    dispatchers: Dispatchers,
    communication: GlobalErrorCommunication.Mutable
) : BackPress.Activity.ViewModel<String>(
    canGoBack,
    communication,
    dispatchers
) {
    private val planetNavigationScreen = PlanetNavigationScreen()
    private val characterFullInfoScreen = CharacterFullInfoScreen()


    init {
        navigate(1)
    }

    fun observeGlobalNavigationCommunication(owner: LifecycleOwner, observer: Observer<Int>)
    {
        globalNavigateCommunication.observe(owner, observer)
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        navigationCommunication.observe(owner, observer)
    }

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>) {
        progressCommunication.observe(owner, observer)
    }


    fun navigate(id: Int) {
        when (id) {
            1 -> navigationCommunication.map(planetNavigationScreen)
            2->navigationCommunication.map(characterFullInfoScreen)
        }
    }
}