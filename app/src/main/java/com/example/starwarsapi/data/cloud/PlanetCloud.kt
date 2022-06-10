package com.example.starwarsapi.data.cloud


import com.example.starwarsapi.data.UrlIdMapper
import com.example.starwarsapi.data.cache.PlanetCache
import com.google.gson.annotations.SerializedName

interface PlanetCloud {
    fun <T> map(mapper: Mapper<T>): T
    class Base(
        @SerializedName("name")
        private val name:String,
        @SerializedName("url")
        private val url:String
    ): PlanetCloud
    {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(name, url)
        }

    }
    interface Mapper<T>
    {
        fun map(name:String,url: String):T

        class MapperToCache(private val curPage:Int,private val netPageUrl:String,private val urlIdMapper: UrlIdMapper):
            Mapper<PlanetCache>
        {
            override fun map(name: String, url: String): PlanetCache {
                return PlanetCache.Base(urlIdMapper.convertToId(url),name,curPage,netPageUrl)
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