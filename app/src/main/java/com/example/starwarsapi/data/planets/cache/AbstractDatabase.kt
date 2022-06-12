package com.example.starwarsapi.data.planets.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharacterDao
import com.example.starwarsapi.data.planets.cache.planets.PlanetCache
import com.example.starwarsapi.data.planets.cache.planets.PlanetsDao

@Database(entities = [CharacterCache.Base::class, PlanetCache.Base::class], version = 1)
abstract class AbstractDatabase:RoomDatabase() {
    abstract fun providePlanetsDao(): PlanetsDao
    abstract fun provideCharactersDao(): CharacterDao
}