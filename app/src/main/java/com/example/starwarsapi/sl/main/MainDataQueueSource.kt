package com.example.starwarsapi.sl.main


class MainDataQueueSource {
    private val dataQueue = DataQueue.Base()
    fun provideDataQueue() = dataQueue
}