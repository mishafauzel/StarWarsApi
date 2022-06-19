package com.example.starwarsapi.data.planets.cloud.planets

import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetService {

    @GET("planets/")
    suspend fun getPlanetsByPage(@Query("page") pageNumber: Int): PlanetsCloud.Base

}