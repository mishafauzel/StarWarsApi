package com.example.starwarsapi.domain.planets

import android.util.Log
import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.items.PagerItem

private const val TAG = "PagerDomain"
interface PagerDomain {
    fun<T> map(mapper: Mapper<T>):T

    data class Base(private val currentPageInt:Int,private val nextPageInt: Int): PagerDomain {
        init {
            Log.d(TAG, ": $nextPageInt")
        }
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(currentPageInt,nextPageInt)
        }
    }

    interface Mapper<T>
    {
        fun map(currentPageInt: Int,nextPageInt: Int):T
        class Base(private val nextPageCommunication: NextPageCommunication.Update): Mapper<PagerItem>
        {
            override fun map(currentPageInt: Int, nextPageInt: Int): PagerItem {
                if(nextPageInt!=-1)
                    return PagerItem.Base(PagerData.Base(currentPageInt,nextPageInt), nextPageCommunication = nextPageCommunication)
                else
                    return PagerItem.ThereIsnoMoreResults()
            }

        }
        class BaseToPlanetsPager:Mapper<PagerData>
        {
            override fun map(currentPageInt: Int, nextPageInt: Int): PagerData {
                return PagerData.Base(currentPageInt,nextPageInt)
            }
        }
    }
}