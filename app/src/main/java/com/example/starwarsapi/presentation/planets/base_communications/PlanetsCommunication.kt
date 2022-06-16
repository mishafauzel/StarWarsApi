package com.example.starwarsapi.presentation.planets.base_communications

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.starwarsapi.presentation.Transformable
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.presentation.Communication

private const val TAG = "PlanetsCommunication"
interface PlanetsCommunication: Communication.Mutable<PlanetsUi>,Transformable<PlanetsUi> {
    class Base :Communication.UiUpdate<PlanetsUi>(), PlanetsCommunication {
        override fun provideViewModelForTransformation(): LiveData<PlanetsUi> {
            Log.d(TAG, "provideViewModelForTransformation: ")
            return mutableLiveData
        }
    }

}