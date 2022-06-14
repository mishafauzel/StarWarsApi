package com.example.starwarsapi.presentation.planets

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.core.Retry
import com.example.starwarsapi.domain.planets.PlanetsInteractor
import com.example.starwarsapi.presentation.main.GlobalNavigateCommunication
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

private const val TAG = "PlanetsViewModel"
class PlanetsViewModel(canGoBackCallback: CanGoBack.Callback,
                       private val interactor: PlanetsInteractor,
                       private val planetsErrorCommunication:PlanetsErrorComunication.Observe,
                       private val progressCommunication: ProgressCommunication.Update,
                       private val listMutator: ListMutator,
                       communication: PlanetsCommunication,
                       dispatchers: Dispatchers
):BackPress.ViewModel<PlanetsUi>(canGoBackCallback,communication,dispatchers),Retry {

    private var canGoBack = true

    init {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(1,atFinish) {
                communication.map(it) }
        }
        listMutator.setTransformable(communication)
    }

    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }



    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }



    fun observeException(lifecycleOwner: LifecycleOwner,observer: Observer<Int>)
    {
        planetsErrorCommunication.observe(lifecycleOwner,observer)
    }
    fun addSomethingWentWrong()
    {
        Log.d(TAG, "addSomethingWentWrong: ")
        communication.map(PlanetsUi.Base(planetUI = listOf(SomethingWentWrongItem(this))))
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

    override fun observe(owner: LifecycleOwner, observer: Observer<PlanetsUi>) {
        this.listMutator.observe(owner,observer)
    }


}