package com.example.starwarsapi.presentation.planets

interface PagerUI {

    class Base(private val currentPageUrl:String,private val nextPageUrl:String): PagerUI
    {}


}
