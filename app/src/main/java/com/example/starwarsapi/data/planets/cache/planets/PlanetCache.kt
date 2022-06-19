package com.example.starwarsapi.data.planets.cache.planets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarsapi.domain.planets.PagerDomain
import com.example.starwarsapi.domain.planets.PlanetDomain

interface PlanetCache {

    fun <T> map(mapper: Mapper<T>): T

    @Entity(tableName = "planet_table")
    data class Base(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "page_id")
        val pageId: Int,
        @ColumnInfo(name = "next_page_id")
        val nextPageId: Int = -1,
    ) : PlanetCache {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, pageId, nextPageId)

    }

    interface Mapper<T> {

        fun map(id: Int, name: String, pageId: Int, nextPageId: Int): T

        class BaseToPagerDomain() : Mapper<PagerDomain> {

            override fun map(
                id: Int,
                name: String,
                pageId: Int,
                nextPageid: Int
            ) = PagerDomain.Base(pageId, nextPageid)

        }

        class BaseToPlanetDomain() : Mapper<PlanetDomain> {

            override fun map(
                id: Int,
                name: String,
                pageId: Int,
                nextPageId: Int
            ) = PlanetDomain.Base(id, name, emptyList())

        }
    }
}