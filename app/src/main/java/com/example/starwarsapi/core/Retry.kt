package com.example.starwarsapi.core

import com.example.starwarsapi.presentation.planets.basedata.PagerData

interface Retry {
    fun retry(pagerData: PagerData)
}