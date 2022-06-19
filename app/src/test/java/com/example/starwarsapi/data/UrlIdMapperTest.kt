package com.example.starwarsapi.data

import com.example.starwarsapi.data.planets.UrlIdMapper
import org.junit.Assert
import org.junit.Test


class UrlIdMapperTest {

    @Test
    fun convertToIdForPlanets() {
        val input = "https://swapi.dev/api/planets/1/"

        val urlIdMapper = UrlIdMapper.IdConverter()
        val expected = 1
        val actual = urlIdMapper.convertToInt(input)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun covertWithIncorrectData() {
        val input = "https://swapi.dev/api/planets/"
        val urlIdMapper = UrlIdMapper.IdConverter()
        val expected = Int.MIN_VALUE
        val actual = urlIdMapper.convertToInt(input)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun convertToUrlFromInt() {

        val input = 1
        val urlIdMapper = UrlIdMapper.IdConverter()
        val expected = "https://swapi.dev/api/planets/1"
        val actual = urlIdMapper.convertToUrl(input)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun convertfromUrlToPage()
    {
        val input= "https://swapi.dev/api/planets/?page=1"
        val urlIdMapper=UrlIdMapper.PageConverter()
        val expected=1
        val actual= urlIdMapper.convertToInt(input)

        Assert.assertEquals(expected,actual)
    }

    @Test
    fun convertfromUrlToPageWithIncorrectData()
    {
        val input= "https://swapi.dev/api/planets/?pag"
        val urlIdMapper=UrlIdMapper.PageConverter()
        val expected=Int.MIN_VALUE
        val actual= urlIdMapper.convertToInt(input)

        Assert.assertEquals(expected,actual)
    }

    @Test
    fun convertFromIntToUrlPage()
    {
        val input=1
        val urlIdMapper=UrlIdMapper.PageConverter()
        val expected="https://swapi.dev/api/planets/?page=1"
        val actual= urlIdMapper.convertToUrl(input)

        Assert.assertEquals(expected,actual)
    }
}
