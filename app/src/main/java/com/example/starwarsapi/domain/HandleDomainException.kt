package com.example.starwarsapi.domain

import android.util.Log
import com.github.johnnysc.coremvvm.data.HandleError
import java.net.UnknownHostException

private const val TAG = "HandleDomainException"
class HandleDomainException:HandleError {
    override fun handle(error: Exception): Exception {
        Log.d(TAG, "handle: $error")
        if (error is UnknownHostException)
            return DomainException.ThereIsNoConnectionDomain()
        else
            return DomainException.ServiceUnavailableException()
    }
}