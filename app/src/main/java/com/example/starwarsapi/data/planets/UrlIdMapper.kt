package com.example.starwarsapi.data.planets

import android.util.Log


interface UrlIdMapper {
    fun convertToInt(url:String):Int
    fun convertToUrl(id:Int,baseUrl: String):String
   object IdConverter: UrlIdMapper
   {
       override fun convertToInt(url: String): Int {
           val urlParts=url.split("/")

           val filtered=urlParts.filter { urlPart->
               urlPart.isNotEmpty()
           }
           return filtered.last().toInt()
       }

       override fun convertToUrl(id: Int,baseUrl:String): String {
           return "$baseUrl$id"
       }
   }
    object PageConverter:UrlIdMapper
    {
        private val baseUrl="https://swapi.dev/api/planets/?page="
        //"https://swapi.dev/api/planets/?page=2
        override fun convertToInt(url: String): Int {

            try {
                val result=url.removePrefix(baseUrl)
                return result.toInt()
            }
            catch (ex:Exception)
            {return -1}

        }

        override fun convertToUrl(id: Int, baseUrl: String): String {
            return "$baseUrl$id"
        }
    }
}