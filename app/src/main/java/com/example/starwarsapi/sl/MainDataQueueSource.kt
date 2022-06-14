package com.example.starwarsapi.sl

class MainDataQueueSource {
    private val dataQueue=DataQueue.Base()
    fun provideDataQueue()=dataQueue
}