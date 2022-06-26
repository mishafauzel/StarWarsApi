package com.example.starwarsapi.presentation.main

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
        navigationCommunication.map(planetNavigationScreen)
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        navigationCommunication.observe(owner, observer)
    }

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>) {
        progressCommunication.observe(owner, observer)
    }



}
