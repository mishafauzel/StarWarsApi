package com.example.starwarsapi.data.planets.cache.planets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlanetsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(listOfPlanets: List<PlanetCache.Base>)

    @Query("SELECT id,name,page_id,next_page_id FROM planet_table WHERE page_id=:pageId")
    fun selectByPage(pageId: Int): List<PlanetCache.Base>
}