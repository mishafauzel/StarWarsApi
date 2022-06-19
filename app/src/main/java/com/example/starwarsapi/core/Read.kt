package com.example.starwarsapi.core

interface Read<i, o> {

    fun read(inputData: i): o

}