package com.example.starwarsapi.presentation

interface DataKeeper {
    interface Write<V> {
        fun write(data: V)
    }

    interface Read<V> {
        fun read(): V
    }

    interface Mutable<V> : Read<V>, Write<V>
    class Base<V>(private var data: V) : Mutable<V> {
        override fun write(data: V) {
            this.data = data
        }

        override fun read(): V {
            return data
        }
    }
}