package com.example.starwarsapi.presentation.planets

interface PagerItem {

    class Base(private val currentPageUrl:String,private val nextPageUrl:String): PagerItem
    {}


}
