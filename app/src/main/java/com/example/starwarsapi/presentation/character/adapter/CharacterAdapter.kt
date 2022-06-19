package com.example.starwarsapi.presentation.character.adapter

import com.example.starwarsapi.presentation.planets.adapter.SomethingWentWrongViewHolderFactoryChain
import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

interface CharacterAdapter {
    class Base : GenericAdapter.Base(
        CharacterFullinfoViewHolderFactoryChain(
            SomethingWentWrongViewHolderFactoryChain(
                ViewHolderFactoryChain.Exception()
            )
        )
    )
}