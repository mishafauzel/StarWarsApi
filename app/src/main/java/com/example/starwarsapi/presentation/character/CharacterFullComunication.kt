package com.example.starwarsapi.presentation.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.presentation.planets.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.PlanetsUi
import com.github.johnnysc.coremvvm.presentation.Communication

interface CharacterFullComunication: Communication.Mutable<CharacterFullUI> {
    class Base :Communication.UiUpdate<CharacterFullUI>(), CharacterFullComunication

}