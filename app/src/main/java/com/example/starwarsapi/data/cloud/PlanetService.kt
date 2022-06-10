package com.example.starwarsapi.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetService {

    @GET("/planets/")
    fun getPlanetsByPage(@Query("page") pageNumber:Int): PlanetsCloud
}