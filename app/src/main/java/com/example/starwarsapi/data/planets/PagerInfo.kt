package com.example.starwarsapi.data.planets

import com.example.starwarsapi.domain.planets.PagerDomain

interface PagerInfo {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val currentPageInt: Int, private val nextPageInt: Int) : PagerInfo {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(currentPageInt, nextPageInt)

    }

    interface Mapper<T> {
        fun map(currentPageInt: Int, nextPageInt: Int): T
        class Base : Mapper<PagerDomain> {

            override fun map(currentPageInt: Int, nextPageInt: Int) =
                PagerDomain.Base(currentPageInt, nextPageInt)

        }
    }
}