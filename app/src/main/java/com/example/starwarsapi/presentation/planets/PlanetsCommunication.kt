package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LiveData
import com.example.starwarsapi.presentation.Transformable
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PlanetsCommunication: Communication.Mutable<PlanetsUi>,Transformable<PlanetsUi> {
    class Base :Communication.UiUpdate<PlanetsUi>(),PlanetsCommunication {
        override fun provideViewModelForTransformation(): LiveData<PlanetsUi> {
            return mutableLiveData
        }
    }

}