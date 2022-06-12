package com.example.starwarsapi.presentation.character

import com.example.starwarsapi.core.Retry
import com.github.johnnysc.coremvvm.data.HandleError

class CharactersErrorHandle(private val errorComunication: CharacterFullInfoErrorCommunication.Mutable):HandleError {
    override fun handle(error: Exception): Exception {
        errorComunication.map(1)
        return error
    }
}