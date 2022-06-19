package com.example.starwarsapi.presentation.planets.base_communications

import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.github.johnnysc.coremvvm.presentation.Communication

interface RetryCommunication {
    interface Observe : Communication.Observe<PagerData>

    interface Update : Communication.Update<PagerData>

    interface Mutable : Communication.Mutable<PagerData>, Observe, Update

    class Base : Communication.SinglePostUpdate<PagerData>(), Mutable
}