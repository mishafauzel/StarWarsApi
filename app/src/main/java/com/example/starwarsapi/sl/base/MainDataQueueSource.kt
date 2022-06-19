package com.example.starwarsapi.sl.base

import com.example.starwarsapi.sl.main.DataQueue

class MainDataQueueSource {

    private val dataQueue = DataQueue.Base()

    fun provideDataQueue() = dataQueue

}
