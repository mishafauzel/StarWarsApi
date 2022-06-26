package com.example.starwarsapi.presentation.character.base_communications

import com.example.starwarsapi.R
import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class ErrorCommunication (
    private val handleError: HandleError,
    private val communication: CharacterFullCommunication,
    private val domaimExceptionMapper: DomainException.Mapper.Factory<List<ItemUi>>
) : HandleError {
    override fun handle(error: Exception): Exception {
        println(error)
        if (error is DomainException.AbstractDomainException) {
            communication.map(
                CharacterFullUI.Base(
                    error.map(
                        domaimExceptionMapper.create(
                            when (error) {
                                is DomainException.ServiceUnavailableException -> R.string.service_unavailable
                                else -> R.string.there_is_no_connection
                            }
                        )
                    )
                )
            )
        } else {
            handleError.handle(error)
        }
        return Exception()

    }

}