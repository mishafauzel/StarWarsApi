package com.example.starwarsapi.presentation.planets

import com.example.starwarsapi.domain.PlanetsInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility

class PlanetsViewModel(canGoBackCallback: CanGoBack.Callback,
                       private val interactor: PlanetsInteractor,
                       progressCommunication: ProgressCommunication.Update,
                       communication: PlanetsCommunication,
                       dispatchers: Dispatchers
):BackPress.ViewModel<PlanetsUi>(canGoBackCallback,communication,dispatchers) {
    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }

    private var canGoBack = true

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    init {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(1,atFinish) { communication.map(it) }
        }
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)
}