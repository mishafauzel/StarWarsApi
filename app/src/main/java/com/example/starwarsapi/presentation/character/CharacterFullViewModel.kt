package com.example.starwarsapi.presentation.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.presentation.character.base_communications.CharacterFullCommunication
import com.example.starwarsapi.presentation.character.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility

class CharacterFullViewModel(
    canGoBackCallback: CanGoBack.Callback,
    private val interactor: CharacterInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    private val retryCommunication: RetryCommunication.Observe,
    communication: CharacterFullCommunication,
    dispatchers: Dispatchers,
    private val idOfCharacter: Int
) : BackPress.ViewModel<CharacterFullUI>(canGoBackCallback, communication, dispatchers) {

    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }
    private var canGoBack = true
    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    init {
        getCharacterFullInfoById()
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)

    fun retry() {
        getCharacterFullInfoById()
    }

    private fun getCharacterFullInfoById() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getCharacterFullInfo(idOfCharacter, atFinish) {
                communication.map(it)
            }
        }
    }

    fun observeRetryCommunication(lifeCycleOwner: LifecycleOwner, observer: Observer<Int>) {
        retryCommunication.observe(lifeCycleOwner, observer)
    }

}