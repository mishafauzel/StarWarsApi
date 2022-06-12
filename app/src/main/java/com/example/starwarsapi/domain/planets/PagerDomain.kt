package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.planets.PagerItem

interface PagerDomain {
    fun<T> map(mapper: Mapper<T>):T

    class Base(private val currentPageUrl:String,private val nextPageUrl:String): PagerDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(currentPageUrl,nextPageUrl)
        }
    }

    interface Mapper<T>
    {
        fun map(currentPageUrl: String,nextPageUrl: String):T
        class Base: Mapper<PagerItem>
        {
            override fun map(currentPageUrl: String, nextPageUrl: String): PagerItem {
                return PagerItem.Base(currentPageUrl,nextPageUrl)
            }

        }
    }
}