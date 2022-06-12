package com.example.starwarsapi.data.planets

interface UrlIdMapper {
    fun convertToId(url:String):Int
    fun convertToUrl(id:Int,baseUrl: String):String
   class IdConverter(): UrlIdMapper
   {
       //"https://swapi.dev/api/planets/1/"
       //"https://swapi.dev/api/people/2/"
       //"https://swapi.dev/api/planets/?page=2
       override fun convertToId(url: String): Int {
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
}