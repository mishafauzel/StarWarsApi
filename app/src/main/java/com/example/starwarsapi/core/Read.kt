package com.example.starwarsapi.core

interface Read<inputData,outputData> {
    fun read(inputData: inputData):outputData
}