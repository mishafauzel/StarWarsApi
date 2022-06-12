package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.DataKeeper
import com.example.starwarsapi.presentation.planets.CharacterItem
import com.example.starwarsapi.presentation.planets.NavigationCommunication

interface CharacterDomain{
    fun <T>map(mapper: Mapper<T>):T
    class Base(private val id: Int,
               private val characterName: String,
               private val birthYear: String): CharacterDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(id, characterName, birthYear)
        }
    }

    interface Mapper<T>
    {
        fun map(id: Int,characterName: String,birthYear: String):T
        class Base(private val navigationCommunication: NavigationCommunication.Update,private val dataKeeper: DataKeeper.Write<Int>): Mapper<CharacterItem>
        {
            override fun map(id: Int, characterName: String, birthYear: String):CharacterItem {
                return CharacterItem(id, characterName, birthYear, navigationCommunication,dataKeeper)
            }
        }
    }
}