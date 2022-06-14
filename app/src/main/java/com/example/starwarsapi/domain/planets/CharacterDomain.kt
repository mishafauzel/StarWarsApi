package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.DataKeeper
import com.example.starwarsapi.presentation.main.GlobalNavigateCommunication
import com.example.starwarsapi.presentation.planets.CharacterItem

import com.example.starwarsapi.sl.DataQueue

interface CharacterDomain{
    fun <T>map(mapper: Mapper<T>):T
    class Base(private val id: Int,
               private val characterName: String,
               private val planetId:Int,
               private val birthYear: String): CharacterDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(id,planetId, characterName, birthYear)
        }
    }

    interface Mapper<T>
    {
        fun map(id: Int,planetId: Int,characterName: String,birthYear: String):T
        class Base(private val navigationCommunication: GlobalNavigateCommunication.Update,private val dataQueue: DataQueue.Update<Any>): Mapper<CharacterItem>
        {
            override fun map(id: Int,planetId: Int, characterName: String, birthYear: String):CharacterItem {
                return CharacterItem(id,planetId, characterName, birthYear, navigationCommunication, dataQueue = dataQueue)
            }
        }
    }
}