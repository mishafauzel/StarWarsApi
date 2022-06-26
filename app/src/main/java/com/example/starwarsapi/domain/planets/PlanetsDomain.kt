package com.example.starwarsapi.domain.planets

import android.graphics.pdf.PdfDocument
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PlanetsDomain {

    suspend fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val pagerDomain: PagerDomain,
        private val planetDomain: List<PlanetDomain>
    ) :
        PlanetsDomain {
        override suspend fun <T> map(mapper: Mapper<T>): T = mapper.map(pagerDomain, planetDomain)

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
        ) : Mapper<PlanetsUi> {

            override suspend fun map(
                pagerDomain: PagerDomain,
                planetsDomain: List<PlanetDomain>
            ):PlanetsUi {
                val listOfItemUI = mutableListOf<ItemUi>()
                planetsDomain.forEach { planetDomain ->
                    listOfItemUI.addAll(planetDomain.map(planetToUI))
                }
                val pagerUi = pagerDomain.map(pagerToUI)
                listOfItemUI.add(pagerUi)
                return PlanetsUi.Base(listOfItemUI)
            }

        }

        class BaseToPagerDomain:Mapper<PagerDomain> {
            override suspend fun map(
                pagerDomain: PagerDomain,
                planetDomain: List<PlanetDomain>
            ): PagerDomain {
                return pagerDomain
            }
        }
    }
}