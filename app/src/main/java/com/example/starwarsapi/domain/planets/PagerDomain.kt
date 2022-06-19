package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
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

        class Base(private val nextPageCommunication: NextPageCommunication.Update) :
            Mapper<PagerItem> {

            override fun map(currentPageInt: Int, nextPageInt: Int): PagerItem {
                val result: PagerItem
                if (nextPageInt == Int.MIN_VALUE)
                    result = PagerItem.ThereIsnoMoreResults()
                else
                    result = PagerItem.Base(
                        PagerData.Base(currentPageInt, nextPageInt),
                        nextPageCommunication = nextPageCommunication
                    )
                return result
            }

        }
    }
}