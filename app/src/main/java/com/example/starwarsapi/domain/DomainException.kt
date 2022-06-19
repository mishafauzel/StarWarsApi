package com.example.starwarsapi.domain

import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.example.starwarsapi.presentation.character.items.SomethingWentWrong
import com.example.starwarsapi.presentation.planets.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.presentation.planets.items.SomethingWentWrongItem
import com.github.johnnysc.coremvvm.core.ManageResources
import com.example.starwarsapi.presentation.character.base_communications.RetryCommunication as CharacterRetry

interface DomainException {

    fun <T> map(mapper: Mapper<T>): T

    abstract class AbstractDomainException(protected val idOfMessage: Int) : DomainException,
        Exception()

    class ThereIsNoConnectionDomain : AbstractDomainException(R.string.there_is_no_connection) {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(idOfMessage)

    }

    class ServiceUnavailableException : AbstractDomainException(R.string.service_unavailable) {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(idOfMessage)

    }

    interface Mapper<T> {

        fun map(messageId: Int): T

        class BaseToPlanetsUi(
            private val manageResources: ManageResources,
            private val retry: RetryCommunication.Update,
            private val pagerData: PagerData
        ) : Mapper<PlanetsUi> {

            override fun map(messageId: Int): PlanetsUi {
                val message = manageResources.string(messageId)
                return PlanetsUi.Base(listOf(SomethingWentWrongItem(retry, message, pagerData)))
            }

        }

        class BaseToCharacterUi(
            private val manageResources: ManageResources,
            private val retry: CharacterRetry.Update,

            ) : Mapper<CharacterFullUI> {

            override fun map(messageId: Int): CharacterFullUI {
                val message = manageResources.string(messageId)
                return CharacterFullUI.Base(listOf(SomethingWentWrong(retry, message)))
            }
        }


        interface Factory<retData> {
            fun create(pagerData: PagerData): Mapper<retData>

            class Base(
                private val manageResources: ManageResources,
                private val retry: RetryCommunication.Update
            ) :
                Factory<PlanetsUi> {

                override fun create(pagerData: PagerData) =
                    BaseToPlanetsUi(manageResources, retry, pagerData)

            }
        }


    }
}