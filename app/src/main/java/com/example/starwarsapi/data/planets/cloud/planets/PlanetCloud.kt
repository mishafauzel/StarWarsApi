package com.example.starwarsapi.data.planets.cloud.planets


import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.google.gson.annotations.SerializedName

interface PlanetCloud {
    fun <T> map(mapper: Mapper<T>): T
    class Base(
        @SerializedName("name")
        private val name:String,
        @SerializedName("url")
        private val url:String,
        @SerializedName("residents")
        private val listOfResidents:List<String>
    ): PlanetCloud
    {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(name, url,listOfResidents)
        }

    }
    interface Mapper<T>
    {
        fun map(name:String,url: String,listOfResidents: List<String>):T

        class MapperToCache(private val curPage:Int,private val netPageUrl:String,private val urlIdMapper: UrlIdMapper):
            Mapper<PlanetCache>
        {
            override fun map(name: String, url: String,listOfResidents: List<String>): PlanetCache {
                return PlanetCache.Base(urlIdMapper.convertToId(url),name,curPage,netPageUrl)
            }


        }
        class MapperToListOfCharacters(private val urlIdMapper: UrlIdMapper):
            Mapper<List<CharacterCache>>
        {
            override fun map(
                name: String,
                url: String,
                listOfResidents: List<String>
            ): List<CharacterCache> {
                return listOfResidents.map {charUrl->
                   CharacterCache.Base(urlIdMapper.convertToId(charUrl),urlIdMapper.convertToId(url))

                }
            }
        }
        interface Factory<T>
        {
            fun create(curPage:Int,netPageUrl:String): Mapper<T>
            class Base(private val urlIdMapper: UrlIdMapper): Factory<PlanetCache>
            {
                override fun create(curPage: Int, nextPageUrl: String): Mapper<PlanetCache> {
                    return MapperToCache(curPage,nextPageUrl,urlIdMapper)
                }
            }
        }

    }

}