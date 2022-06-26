package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.domain.planets.PlanetsInteractor
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.LiveDataTransformator
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication

import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility


class PlanetsViewModel(
    canGoBackCallback: CanGoBack.Callback,
    private val interactor: PlanetsInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    private val listMutator: LiveDataTransformator<PlanetsUi, PlanetsUi>,
    communication: PlanetsCommunication,
    dispatchers: Dispatchers,
    getInfoCommunication: GetInfoCommunication
) : BackPress.ViewModel<PlanetsUi>(canGoBackCallback, communication, dispatchers){


    private var canGoBack = true
    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }
    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    init {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        getInfoCommunication.setNextPageFun { getInfoNextPage() }
        listMutator.setTransformable(communication)
        getInfoNextPage()
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)

    fun getInfoNextPage() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(
                atFinish = atFinish,
                successful = { result ->
                    communication.map(result)
                })
        }
    }

    fun observeListMutator(owner: LifecycleOwner, observer: Observer<PlanetsUi>) {
        this.listMutator.observeOutput(owner, observer)
    }
}