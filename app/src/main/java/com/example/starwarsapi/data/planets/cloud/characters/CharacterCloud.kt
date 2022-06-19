package com.example.starwarsapi.data.planets.cloud.characters

import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.google.gson.annotations.SerializedName

interface CharacterCloud {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        @SerializedName("name") private val name: String,
        @SerializedName("birth_year") private val birthYear: String,
        @SerializedName("url") private val url: String
    ) : CharacterCloud {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(name, birthYear, url)

    }

    interface Mapper<T> {

        fun map(name: String, birthYear: String, url: String): T

        class BaseToCharacterCache(
            private val urlIdMapper: UrlIdMapper,
            private val planetId: Int
        ) : Mapper<CharacterCache> {

            override fun map(name: String, birthYear: String, url: String) =
                CharacterCache.Base(urlIdMapper.convertToInt(url), planetId, name, birthYear)

        }

        interface Factory<T : Mapper<*>, B> {

            fun create(arg: B): T

            class Base(private val urlIdMapper: UrlIdMapper) :
                Factory<Mapper<CharacterCache>, Int> {

                override fun create(arg: Int) = BaseToCharacterCache(urlIdMapper, arg)

            }
        }
    }
}