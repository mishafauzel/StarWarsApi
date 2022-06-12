package com.example.starwarsapi.presentation.character

import com.github.johnnysc.coremvvm.presentation.Communication

interface CharacterFullInfoErrorCommunication {
    interface Observe : Communication.Observe<Int>

    interface Update : Communication.Update<Int>

    interface Mutable : Communication.Mutable<Int>, Observe, Update

    class Base : Communication.SinglePostUpdate<Int>(), Mutable
}