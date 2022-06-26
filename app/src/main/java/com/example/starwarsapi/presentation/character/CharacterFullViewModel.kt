package com.example.starwarsapi.presentation.character

import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.character.base_communications.CharacterFullCommunication
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
    communication: CharacterFullCommunication,
    dispatchers: Dispatchers,
    getInfoCommunication: GetInfoCommunication
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
        getInfoCommunication.setNextPageFun { fetch() }
        fetch()
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)


    private fun fetch() {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getCharacterFullInfo(atFinish) {
                communication.map(it)
            }
        }
    }


}