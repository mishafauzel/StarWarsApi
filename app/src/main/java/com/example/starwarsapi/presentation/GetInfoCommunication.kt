package com.example.starwarsapi.presentation

interface GetInfoCommunication {

    fun setNextPageFun(nextPageFun:()->Unit)

    fun getNextPage()

    class Base: GetInfoCommunication {

        private lateinit var nextPageFun:()->Unit

        override fun setNextPageFun(nextPageFun: ()-> Unit) {
            this.nextPageFun=nextPageFun
        }

        override fun getNextPage() {
            nextPageFun.invoke()
        }
    }

}