package com.example.starwarsapi.data.planets

import com.example.starwarsapi.domain.planets.PagerDomain

interface PagerInfo {
    fun<T> map(mapper: Mapper<T>):T


    class Base(private val currentPageUrl:String,private val nextPageUrl:String): PagerInfo {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(currentPageUrl,nextPageUrl)
        }


    }

    interface Mapper<T>
    {
        fun map(currentPageUrl: String,nextPageUrl: String):T
        class Base: Mapper<PagerDomain>
        {
            override fun map(currentPageUrl: String, nextPageUrl: String): PagerDomain {
                return PagerDomain.Base(currentPageUrl,nextPageUrl)
            }
        }
        class BaseToDb: Mapper<Pair<String, String>>
        {
            override fun map(currentPageUrl: String, nextPageUrl: String): Pair<String,String> {
                return Pair(currentPageUrl,nextPageUrl)
            }
        }
    }
}