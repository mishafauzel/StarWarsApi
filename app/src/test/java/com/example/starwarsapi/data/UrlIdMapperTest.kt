package com.example.starwarsapi.data

import org.junit.Test
//"https://swapi.dev/api/planets/1/"
class UrlIdMapperTest {

    @Test
    fun convertToId() {
        UrlIdMapper.BaseUrlPlanet("https://swapi.dev/api/planets/")
    }

    @Test
    fun convertToUrl() {
    }
}