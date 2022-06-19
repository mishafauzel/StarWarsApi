package com.example.starwarsapi.sl.main

import java.util.*


interface DataQueue {
    interface Update<T> {
        fun update(data: T)
    }

    interface Read<T> {
        fun read(): T
    }

    interface Mutable<T> : Update<T>, Read<T>

    class Base : Mutable<Any> {
        private val dataQueue: Queue<Any> = LinkedList()
        override fun update(data: Any) {
            dataQueue.offer(data)
        }

        override fun read(): Any {
            val data = dataQueue.poll()
            if (data == null)
                throw ThereIsNoDataException()
            else
                return data

        }

        class ThereIsNoDataException() : NullPointerException("There is no data in data queue")
    }

}