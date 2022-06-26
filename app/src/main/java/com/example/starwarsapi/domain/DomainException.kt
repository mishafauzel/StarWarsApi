package com.example.starwarsapi.domain

import com.example.starwarsapi.presentation.GetInfoCommunication

import com.example.starwarsapi.presentation.planets.items.SomethingWentWrongItem
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface DomainException {

    fun <T> map(mapper: Mapper<T>): T

    abstract class AbstractDomainException() : DomainException,
        Exception()

    class ThereIsNoConnectionDomain : AbstractDomainException() {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map()

    }

    class ServiceUnavailableException : AbstractDomainException() {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map()

    }

    interface Mapper<T> {

        fun map(): T

        class BaseToItemUI(
            private val messageId: Int,
            private val manageResources: ManageResources,
            private val getInfoCommunication: GetInfoCommunication
        ) : Mapper<List<ItemUi>> {

            override fun map(): List<ItemUi> {
                return listOf(
                    SomethingWentWrongItem(
                        manageResources.string(messageId),
                        getInfoCommunication
                    )
                )
            }
        }

        interface Factory<retData> {
            fun create(messageId: Int): Mapper<retData>

            class Base(
                private val manageResources: ManageResources,
                private val getInfoCommunication: GetInfoCommunication

            ) :
                Factory<List<ItemUi>> {

                override fun create(messageId: Int) =
                    BaseToItemUI(messageId, manageResources, getInfoCommunication)

            }
        }


    }
}