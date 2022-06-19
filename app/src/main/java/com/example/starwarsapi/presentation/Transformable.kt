package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData

interface Transformable<itemType> {

    fun provideViewModelForTransformation(): LiveData<itemType>

}