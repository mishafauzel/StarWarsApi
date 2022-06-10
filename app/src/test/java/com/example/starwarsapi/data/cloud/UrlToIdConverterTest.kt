package com.example.starwarsapi.data.cloud

import org.junit.Assert.*

import org.junit.Test

class UrlToIdConverterTest {

    @Test
    fun convert() {
        val urlConverter=UrlToIdConverter.Base()
        val expected=1
        val actual=urlConverter.convert("https://swapi.dev/api/planets/1/")
        assertEquals(actual,expected)
    }
}