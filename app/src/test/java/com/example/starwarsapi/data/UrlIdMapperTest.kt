package com.example.starwarsapi.data

import com.example.starwarsapi.data.planets.UrlIdMapper
import org.junit.Assert
import org.junit.Test
//"https://swapi.dev/api/planets/1/"
class UrlIdMapperTest {

    @Test
    fun convertToIdForPlanets() {
        val input="https://swapi.dev/api/planets/1/"
        //"https://swapi.dev/api/people/2/"
        val urlIdMapper= UrlIdMapper.IdConverter()
        val expected=1
        val actual=urlIdMapper.convertToId(input)

        Assert.assertEquals(expected,actual)
    }
    @Test
    fun convertToIdForCharacters()
    {
        val input="https://swapi.dev/api/people/2/"
        //"https://swapi.dev/api/people/2/"
        val urlIdMapper= UrlIdMapper.IdConverter()
        val expected=2
        val actual=urlIdMapper.convertToId(input)

        Assert.assertEquals(expected,actual)
    }

    @Test
    fun convertToUrl() {
    }
}