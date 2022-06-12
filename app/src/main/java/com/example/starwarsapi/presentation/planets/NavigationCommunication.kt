package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.NavigationScreen


interface NavigationCommunication {
    interface Update : Communication.Update<Int>

    interface Observe : Communication.Observe<Int>

    interface Mutable : Update, Observe

    class Base :
        Communication.SinglePostUpdate<Int>(),
        Mutable

}