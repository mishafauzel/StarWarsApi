package com.example.starwarsapi.presentation.character.base_communications

import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.github.johnnysc.coremvvm.presentation.Communication

interface CharacterFullCommunication : Communication.Mutable<CharacterFullUI> {
    class Base : Communication.UiUpdate<CharacterFullUI>(), CharacterFullCommunication

}