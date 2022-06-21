package com.example.starwarsapi.data.planets

import org.junit.Assert.*

import org.junit.Test

class UrlIdMapperTest {

    @Test
    fun convertToInt() {
        val urlIdMapper=UrlIdMapper.IdConverter()
        val input="https://swapi.dev/api/planets/1"
        val expected=1

        val actual = urlIdMapper.convertToInt(input)
        assertEquals(expected,actual)
    }

    @Test
    fun convertToUrl() {
    }
}