package com.example.starwarsapi.presentation

import android.util.Log
import androidx.lifecycle.*

private const val TAG = "LiveDataTransformator"
abstract class LiveDataTransformator<from,to>() {
    protected val outputLiveData:MediatorLiveData<to> = MediatorLiveData()

    fun setTransformable(transformable: Transformable<from>)
    {
        outputLiveData.addSource(transformable.provideViewModelForTransformation()){inputData->
            val output=transform(inputData)
            Log.d(TAG, "setTransformable: ${output}")
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