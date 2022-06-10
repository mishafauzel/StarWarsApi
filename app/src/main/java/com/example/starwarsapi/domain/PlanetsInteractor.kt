package com.example.starwarsapi.domain

import com.example.starwarsapi.presentation.planets.PlanetsUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor

interface PlanetsInteractor {
    suspend fun getListOfPlanetsByPage(
        pageNumber: Int, atFinish: () -> Unit,
        successful: (PlanetsUi) -> Unit
    )

    class Base(
        private val mapper: PlanetsDomain.Mapper<PlanetsUi>,
        private val repository: PlanetsRepository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : PlanetsInteractor, Interactor.Abstract(dispatchers, handleError) {
        override suspend fun getListOfPlanetsByPage(
            pageNumber: Int,
            atFinish: () -> Unit,
            successful: (PlanetsUi) -> Unit
        ) = handle(successful, atFinish) {
            val data = repository.selectPlanetsByPage(pageNumber)
            return@handle data.map(mapper)

        }
    }
}