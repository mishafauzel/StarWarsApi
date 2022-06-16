package com.example.starwarsapi.data.planets.cache.characters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character_table WHERE planet_id=:planetId")
    fun selectCharByPlanetId(planetId: Int): List<CharacterCache.Base>

    @Query("SELECT * FROM character_table WHERE id=:id")
    fun selectCharById(id: Int): CharacterCache.Base

    @Insert(onConflict = REPLACE)
    fun insertListOfCharacters(list: List<CharacterCache.Base>)

    @Insert(onConflict = REPLACE)
    fun insertCharacter(characterCache: CharacterCache.Base)

}