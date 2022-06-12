package com.example.starwarsapi.data.planets.cache.planets

import androidx.room.*

@Dao
interface PlanetsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(listOfPlanets:List<PlanetCache.Base>)

    @Query("SELECT id,name,page_id,next_page_url FROM planet_table WHERE page_id=:pageUrl")
    fun selectByPage(pageUrl:Int):List<PlanetCache.Base>
}