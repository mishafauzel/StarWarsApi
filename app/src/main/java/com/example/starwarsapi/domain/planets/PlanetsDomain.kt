package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.planets.PlanetsUi
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PlanetsDomain {
    suspend fun <T> map(mapper: Mapper<T>): T
    class Base(private val pagerDomain: PagerDomain, private val planetDomain: List<PlanetDomain>) :
        PlanetsDomain {
        override suspend fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(pagerDomain, planetDomain)
        }
    }

    interface Mapper<T> {
        suspend fun map(pagerDomain: PagerDomain, planetDomain: List<PlanetDomain>): T
        class BaseToWithResidence(private val planetToPlanetWithResidence: PlanetDomain.Mapper<PlanetDomain>) :
            Mapper<PlanetsDomain> {
            override suspend fun map(
                pagerDomain: PagerDomain,
                planetDomain: List<PlanetDomain>
            ): PlanetsDomain {
                val planetWithResidence = planetDomain.map {
                    it.map(planetToPlanetWithResidence)
                }
                return Base(pagerDomain, planetWithResidence)
            }
        }

        class BaseToUI(
            private val planetToUI: PlanetDomain.Mapper<List<ItemUi>>,
            private val pagerToUI: PagerDomain.Mapper.Base
        ) :
            Mapper<PlanetsUi> {
            override suspend fun map(
                pagerDomain: PagerDomain,
                planetsDomain: List<PlanetDomain>
            ): PlanetsUi {
                val listOfItemUI = mutableListOf<ItemUi>()
                planetsDomain.forEach { planetDomain ->
                    listOfItemUI.addAll(planetDomain.map(planetToUI))

                }

                val pagerUi = pagerDomain.map(pagerToUI)
                return PlanetsUi.Base(pagerUi, listOfItemUI)
            }

        }
    }
}