package com.example.starwarsapi.presentation.planets.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

interface PlanetsAdapter {
    class Planets : GenericAdapter.Base(
        PlanetsViewHolderFactoryChain(
            EmptyCharacterHolderFactoryChain(
                CharacterViewHolderFactoryChain(
                    SomethingWentWrongViewHolderFactoryChain(
                        GetNextPageDataViewHolderChain(
                            ThereIsNoDataAnymoreViewHolderFactoryChain(ViewHolderFactoryChain.Exception())
                        )
                    )
                )
            )
        )
    )
}