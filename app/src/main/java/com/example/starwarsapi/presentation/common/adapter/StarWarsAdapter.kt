package com.example.starwarsapi.presentation.common.adapter

import com.example.starwarsapi.presentation.characters.adapter.CharacterViewHolderFactoryChain
import com.example.starwarsapi.presentation.planets.adapter.PlanetsViewHolderFactoryChain
import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

interface StarWarsAdapter {
    class Planets : GenericAdapter.Base(
        PlanetsViewHolderFactoryChain(ViewHolderFactoryChain.Exception())
    )

    class Characters :
        GenericAdapter.Base(CharacterViewHolderFactoryChain(ViewHolderFactoryChain.Exception()))
}