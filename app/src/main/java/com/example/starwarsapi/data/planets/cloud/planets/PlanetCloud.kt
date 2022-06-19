package com.example.starwarsapi.data.planets.cloud.planets


import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.UrlIdMapper.IdConverter
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.google.gson.annotations.SerializedName

interface PlanetCloud {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("name")
        private val name: String,
        @SerializedName("url")
        private val url: String,
        @SerializedName("residents")
        private val listOfResidents: List<String>
    ) : PlanetCloud {

        override fun <T> map(mapper: Mapper<T>): T =
            mapper.map(name, url, listOfResidents)

    }

    interface Mapper<T> {

        fun map(name: String, url: String, listOfResidents: List<String>): T

        class MapperToCache(
            private val curPage: Int,
            private val nextPageUrl: String,
            private val pageConverter: UrlIdMapper,
            private val idConverter: IdConverter
        ) : Mapper<PlanetCache> {

            override fun map(
                name: String,
                url: String,
                listOfResidents: List<String>
            ) = PlanetCache.Base(
                idConverter.convertToInt(url),
                name,
                curPage,
                pageConverter.convertToInt(nextPageUrl)
            )

        }

        interface Factory<T> {

            fun create(curPage: Int, nextPageUrl: String): Mapper<T>

            class Base(
                private val pageConverter: UrlIdMapper,
                private val idConverter: IdConverter
            ) : Factory<PlanetCache> {

                override fun create(curPage: Int, nextPageUrl: String) =
                    MapperToCache(curPage, nextPageUrl, pageConverter, idConverter)

            }
        }

        class MapperToListOfCharacters(
            private val idConverter: IdConverter
        ) : Mapper<List<CharacterCache>> {

            override fun map(
                name: String,
                url: String,
                listOfResidents: List<String>
            ) = listOfResidents.map { charUrl ->
                CharacterCache.Base(
                    idConverter.convertToInt(charUrl),
                    idConverter.convertToInt(url)
                )
            }
        }
    }

}