package com.example.starwarsapi.data

interface UrlIdMapper {
    fun convertToId(url:String):Int
    fun convertToUrl(id:Int):String
    class BaseUrlPlanet(private val baseUrl:String): UrlIdMapper
    {
        override fun convertToId(url: String): Int {
            val splitedString=url.split('/')
            splitedString.filter {string->
                string.isNotEmpty()
            }
            return splitedString.last().toInt()
        }

        override fun convertToUrl(id: Int): String {
            return baseUrl+id
        }
    }
    class BaseUrlPage()
}