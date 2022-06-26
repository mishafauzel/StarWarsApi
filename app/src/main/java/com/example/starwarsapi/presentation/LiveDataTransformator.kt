package com.example.starwarsapi.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer


abstract class LiveDataTransformator<from, to>() {
    protected val outputLiveData: MediatorLiveData<to> = MediatorLiveData()

    fun setTransformable(transformable: Transformable<from>) {
        outputLiveData.addSource(transformable.provideViewModelForTransformation()) { inputData ->
            val output = transform(inputData)
            changeValue(output)
        }
    }


    fun observeOutput(lifecycleOwner: LifecycleOwner, observer: Observer<to>) {
        outputLiveData.observe(lifecycleOwner, observer)
    }

    private fun changeValue(value: to) {
        outputLiveData.value = value!!
    }

    abstract fun transform(from: from): to

    abstract fun changeIdList(id: Int)
}