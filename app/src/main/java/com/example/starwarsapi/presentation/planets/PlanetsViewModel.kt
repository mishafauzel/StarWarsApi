package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.core.Retry
import com.example.starwarsapi.domain.planets.PlanetsInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class PlanetsViewModel(canGoBackCallback: CanGoBack.Callback,
                       private val interactor: PlanetsInteractor,
                       private val navigationCommunication: NavigationCommunication.Observe,
                       private val planetsErrorCommunication:PlanetsErrorComunication.Observe,
                       private val progressCommunication: ProgressCommunication.Update,
                       communication: PlanetsCommunication,
                       dispatchers: Dispatchers
):BackPress.ViewModel<PlanetsUi>(canGoBackCallback,communication,dispatchers),Retry {
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
            interactor.getListOfPlanetsByPage(1,atFinish) {
                communication.map(it) }
        }
    }
    fun observeNavigation(owner: LifecycleOwner,observer: Observer<Int>)
    {
        navigationCommunication.observe(owner,observer)
    }
    fun observeException(lifecycleOwner: LifecycleOwner,observer: Observer<Int>)
    {
        planetsErrorCommunication.observe(lifecycleOwner,observer)
    }
    fun addSomethingWentWrong()
    {
        communication.map(PlanetsUi.Exception(listOf(SomethingWentWrongItem(this))))
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)



    override fun retry() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(1,atFinish) {
                communication.map(it) }
        }
    }


}