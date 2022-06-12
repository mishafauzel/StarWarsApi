package com.example.starwarsapi.presentation.character

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.core.Retry
import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.domain.planets.PlanetsInteractor
import com.example.starwarsapi.presentation.DataKeeper
import com.example.starwarsapi.presentation.planets.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.PlanetsErrorComunication
import com.example.starwarsapi.presentation.planets.SomethingWentWrongItem
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility

class CharacterFullViewModel(canGoBackCallback: CanGoBack.Callback,
                             private val interactor: CharacterInteractor,
                             private val progressCommunication: ProgressCommunication.Update,
                             private val errorCommunication: CharacterFullInfoErrorCommunication.Observe,
                             communication: CharacterFullComunication,
                             dispatchers: Dispatchers,
                             private val dataKeeper: DataKeeper.Read<Int>
):BackPress.ViewModel<CharacterFullUI>(canGoBackCallback,communication,dispatchers),Retry {

    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }

    private var canGoBack = true

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }
    init {
        Log.d("TAG", "dataKeeper:${dataKeeper.read()} ")
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(dataKeeper.read(),atFinish) {
                communication.map(it) }
        }
    }


    fun observeException(lifecycleOwner: LifecycleOwner, observe: Observer<Int>) {
        errorCommunication.observe(lifecycleOwner,observe)
    }

    fun addSomethingWentWrong() {
        communication.map(CharacterFullUI.Exception(listOf(SomethingWentWrongItem(this))))
    }
    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)



    override fun retry() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(dataKeeper.read(),atFinish) {
                communication.map(it) }
        }
    }
}