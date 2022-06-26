package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.planets.items.PagerItem


interface PagerDomain {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(private val currentPageInt: Int, private val nextPageInt: Int) : PagerDomain {

        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(currentPageInt, nextPageInt)

        }
    }

    interface Mapper<T> {

        fun map(currentPageInt: Int, nextPageInt: Int): T

        class Base(private val getInfoCommunication: GetInfoCommunication) :
            Mapper<PagerItem> {

            override fun map(currentPageInt: Int, nextPageInt: Int): PagerItem {
                return if (nextPageInt == Int.MIN_VALUE)
                    PagerItem.ThereAreNoMoreResults()
                else
                    PagerItem.Base(
                        getInfoCommunication = getInfoCommunication
                    )
            }

        }

        class BaseToInt:Mapper<Int>
        {
            override fun map(currentPageInt: Int, nextPageInt: Int): Int {
                return nextPageInt
            }
        }
    }
}