package com.example.starwarsapi.domain

import com.github.johnnysc.coremvvm.data.HandleError
import java.net.UnknownHostException


class HandleDomainException : HandleError {

    override fun handle(error: Exception): Exception {
        return if (error is UnknownHostException)
            DomainException.ThereIsNoConnectionDomain()
        else
            DomainException.ServiceUnavailableException()
    }
}