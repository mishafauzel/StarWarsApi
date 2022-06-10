package com.example.starwarsapi.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.starwarsapi.data.UrlIdMapper
import com.example.starwarsapi.domain.PagerDomain
import com.example.starwarsapi.domain.PlanetDomain

interface PlanetCache {
    fun <T> map(mapper: Mapper<T>):T


    @Entity(tableName = "planet_table")
    data class Base(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        private val id: Int,
        @ColumnInfo(name = "name")
        private val name: String,
        @ColumnInfo(name="page_id")
        private val pageId:Int,
        @ColumnInfo(name="next_page_url")
        private val nextPageUrl:String

    ): PlanetCache
    {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(id,name,pageId, nextPageUrl)
        }

    }
    interface Mapper<T>
    {
        fun map(id:Int, name:String, pageId:Int, nextPageUrl: String):T

        class BaseToPagerDomain(private val urlIdMapper: UrlIdMapper): Mapper<PagerDomain>
        {
            override fun map(
                id: Int,
                name: String,
                pageId: Int,
                nextPageUrl: String
            ): PagerDomain {
                return PagerDomain.Base(urlIdMapper.convertToUrl(pageId), nextPageUrl)
            }
        }
        class BaseToPlanetDomain(): Mapper<PlanetDomain>
        {
            override fun map(
                id: Int,
                name: String,
                pageId: Int,
                nextPageUrl: String
            ): PlanetDomain {
                return PlanetDomain.Base(id,name)
            }
        }
    }


}