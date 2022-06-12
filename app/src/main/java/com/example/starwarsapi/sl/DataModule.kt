package com.example.starwarsapi.sl

import com.example.starwarsapi.presentation.DataKeeper

class DataModule {
    private val dataKeeper=DataKeeper.Base<Int>(0)
    fun getDataKeeper():DataKeeper.Mutable<Int>
    {return dataKeeper}
}