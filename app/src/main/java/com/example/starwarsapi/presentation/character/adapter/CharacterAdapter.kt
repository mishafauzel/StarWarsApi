package com.example.starwarsapi.presentation.character.adapter

import android.view.View
import com.example.starwarsapi.presentation.planets.adapter.SomethingWentWrongViewHolderFactoryChain
import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
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