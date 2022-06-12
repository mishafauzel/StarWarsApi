package com.example.starwarsapi.data.character.cloud

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud
import com.example.starwarsapi.presentation.character.CharacterFullUI
import com.google.gson.annotations.SerializedName

interface CharacterFullInfoCloud {

    fun<T> map(mapper: Mapper<T>):T
    data class Base(
        @SerializedName("name") private val name: String,
        @SerializedName("birth_year") private val birthYear: String,
        @SerializedName("url") private val url:String,
        @SerializedName("homeworld")private val homeWorldUrl:String,
        @SerializedName("hair_color")private val hairColor:String,
        @SerializedName("skin_color")private val skinColor:String,
        @SerializedName("gender")private val gender:String,
        @SerializedName("mass")private val mass:String,
        @SerializedName("height")private val height:String
    ): CharacterFullInfoCloud
    {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(name,birthYear,url,homeWorldUrl,hairColor, skinColor, gender, mass, height)
        }
    }

    interface Mapper<T> {
        fun map(name: String,birthYear: String,url:String,homeWorldUrl:String, hairColor:String,skinColor:String,gender:String,mass:String,height:String):T
        class BaseToCharacterCache(private val urlIdMapper: UrlIdMapper,
                                   ): Mapper<CharacterCache>
        {
            override fun map(
                name: String,
                birthYear: String,
                url: String,
                homeWorldUrl: String,
                hairColor: String,
                skinColor: String,
                gender: String,
                mass: String,
                height: String
            ): CharacterCache {
               return CharacterCache.Base(urlIdMapper.convertToId(url),urlIdMapper.convertToId(homeWorldUrl),name,birthYear,hairColor,skinColor,gender,mass,height)
            }

        }

    }
}