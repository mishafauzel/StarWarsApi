package com.example.starwarsapi.presentation.planets

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import java.net.NetworkInterface
//int потому что не помню как можно убрать поставить значение ничего.а уже 2 часа ночи
interface PlanetsErrorComunication {
    interface Observe : Communication.Observe<Int>

    interface Update : Communication.Update<Int>

    interface Mutable : Communication.Mutable<Int>, Observe, Update

    class Base : Communication.SinglePostUpdate<Int>(), Mutable
}