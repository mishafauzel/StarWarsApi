package com.example.starwarsapi.data.planets.cache.planets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.domain.planets.PagerDomain
import com.example.starwarsapi.domain.planets.PlanetDomain

interface PlanetCache {
    fun <T> map(mapper: Mapper<T>):T


    @Entity(tableName = "planet_table")
    data class Base(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
         val id: Int,
        @ColumnInfo(name = "name")
         val name: String,
        @ColumnInfo(name="page_id")
         val pageId:Int,
        @ColumnInfo(name="next_page_url")
         val nextPageUrl:String,



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
                return PagerDomain.Base(urlIdMapper.convertToUrl(pageId,"https://swapi.dev/api/planets/?page="), nextPageUrl)
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
                return PlanetDomain.Base(id,name, emptyList())
            }
        }
    }


}