package com.example.starwarsapi.presentation.character

import com.github.johnnysc.coremvvm.core.ManageResources

class ManageResourceTest:ManageResources {
    override fun string(id: Int): String {
        return "Some string from resources"
    }
}