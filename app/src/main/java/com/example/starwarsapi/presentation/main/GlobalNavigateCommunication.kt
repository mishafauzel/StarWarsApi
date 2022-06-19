package com.example.starwarsapi.presentation.main

import com.github.johnnysc.coremvvm.presentation.Communication

interface GlobalNavigateCommunication {
    interface Update : Communication.Update<Int>

    interface Observe : Communication.Observe<Int>

    interface Mutable : Update, Observe

    class Base : Communication.SingleUiUpdate<Int>(), Mutable

}
