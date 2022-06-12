package com.example.starwarsapi.presentation.planets

import com.example.starwarsapi.core.Retry
import com.github.johnnysc.coremvvm.data.HandleError

class PlanetsErrorHandle(private val errorComunication: PlanetsErrorComunication.Update):HandleError {
    override fun handle(error: Exception): Exception {
        errorComunication.map(1)
        return error
    }
}