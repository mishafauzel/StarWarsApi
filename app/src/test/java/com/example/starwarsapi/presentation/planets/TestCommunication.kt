package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi

class TestCommunication: PlanetsCommunication {
    private lateinit var planetsUI:PlanetsUi

    fun checkValue()=planetsUI
    override fun map(data: PlanetsUi) {
        this.planetsUI=data
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<PlanetsUi>) {
    }

    override fun provideViewModelForTransformation(): LiveData<PlanetsUi> {
        return MutableLiveData()
    }
}