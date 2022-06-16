package com.example.starwarsapi.presentation.planets.basedata

interface PagerData {
    fun <T> map(mapper: Mapper<T>): T
    data class Base(private val currentPageId: Int, private val nextPageId: Int) : PagerData {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(currentPageId, nextPageId)
        }
    }

    interface Mapper<T> {
        fun map(currentPageId: Int, nextPageId: Int): T

        class Base : Mapper<Int> {
            override fun map(currentPageId: Int, nextPageId: Int): Int {
                return nextPageId
            }
        }
    }


}