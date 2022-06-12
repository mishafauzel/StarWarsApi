package com.example.starwarsapi.presentation.planets

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface PlanetsCommunication: Communication.Mutable<PlanetsUi> {
    class Base :Communication.UiUpdate<PlanetsUi>(),PlanetsCommunication

}