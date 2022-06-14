package com.example.starwarsapi.presentation

import androidx.lifecycle.*
import com.example.starwarsapi.presentation.planets.PlanetsUi
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi


abstract class LiveDataTransformator<from,to>() {
    protected val outputLiveData:MediatorLiveData<to> = MediatorLiveData()

    fun setTransformable(transformable: Transformable<from>)
    {
        outputLiveData.addSource(transformable.provideViewModelForTransformation()){inputData->
            val output=transform(inputData)
            changeValue(output)

        }
    }



    fun observeOutput(lifecycleOwner: LifecycleOwner,observer: Observer<to>)
    {
        outputLiveData.observe(lifecycleOwner,observer)
    }
    fun changeValue(value:to)
    {
        outputLiveData.value=value!!
    }
    abstract fun transform(from: from):to
}