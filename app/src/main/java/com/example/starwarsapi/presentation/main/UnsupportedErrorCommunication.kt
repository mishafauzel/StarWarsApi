package com.example.starwarsapi.presentation.main

import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication

class UnsupportedErrorCommunication(private val globalErrorCommunication: GlobalErrorCommunication.Base,private val resources: ManageResources) :
    HandleError {
    override fun handle(error: Exception): Exception {
        globalErrorCommunication.map(resources.string(R.string.UnknownException))
        return Exception()
    }

}