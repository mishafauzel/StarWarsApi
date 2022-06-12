package com.example.starwarsapi.domain.planets

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
        private val mapperDomainToDomainWithResidence: PlanetsDomain.Mapper<PlanetsDomain>,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : PlanetsInteractor, Interactor.Abstract(dispatchers, handleError) {
        override suspend fun getListOfPlanetsByPage(
            pageNumber: Int,
            atFinish: () -> Unit,
            successful: (PlanetsUi) -> Unit
        ) = handle(successful, atFinish) {
            val data = repository.selectPlanetsByPage(pageNumber)
            val dataWithResidence=data.map(mapperDomainToDomainWithResidence)
            return@handle dataWithResidence.map(mapper)

        }
    }
}