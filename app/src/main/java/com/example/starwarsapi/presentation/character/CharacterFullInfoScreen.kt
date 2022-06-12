package com.example.starwarsapi.presentation.character

import com.example.starwarsapi.presentation.ShowWithExtraInfoStrategy
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class CharacterFullInfoScreen(showStrategy: ShowStrategy = ShowStrategy.REPLACE) :
    NavigationScreen("CharacterFullInfoStrategy", CharacterFullFragment::class.java, showStrategy)