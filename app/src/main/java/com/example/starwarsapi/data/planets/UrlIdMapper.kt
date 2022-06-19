package com.example.starwarsapi.data.planets


interface UrlIdMapper {
    fun convertToInt(url: String): Int

    fun convertToUrl(id: Int): String


    class IdConverter(
        private val splitter: Split = Split.Base("/"),
        private val filter: FilterList<String> = FilterList.Base(),
        private val concatenation: Concatenation<Int> = Concatenation.Base("https://swapi.dev/api/planets/"),
        private val stringConverter: StringConverter<Int> = StringConverter.Base()
    ) : UrlIdMapper {

        override fun convertToInt(url: String): Int {
            val urlParts = splitter.split(url)
            return stringConverter.convert(filter.filter(urlParts))
        }

        override fun convertToUrl(id: Int) = concatenation.concat(id)

    }

    class PageConverter(
        private val stringConverter: StringConverter<Int> = StringConverter.Base(),
        private val prefixRemoving: PrefixRemoving = PrefixRemoving.Base("https://swapi.dev/api/planets/?page="),
        private val concatenation: Concatenation<Int> = Concatenation.Base("https://swapi.dev/api/planets/?page=")
    ) : UrlIdMapper {

        override fun convertToInt(input: String) =
            stringConverter.convert(prefixRemoving.removePrefix(input))

        override fun convertToUrl(id: Int) = concatenation.concat(id)

    }

    interface StringConverter<out> {
        val emptyResult:out

        fun convert(input: String): out

        class Base() : StringConverter<Int> {

            override val emptyResult: Int
                get() = Int.MIN_VALUE

            override fun convert(input: String) = try {
                input.toInt()
            } catch (ex: Exception) {
                emptyResult
            }

        }

    }

    interface Split {

        fun split(input: String): List<String>

        class Base(private val delimitres: String) : Split {

            override fun split(input: String) = input.split(delimitres)

        }
    }

    interface FilterList<type> {
        fun filter(i: List<type>): type

        class Base(private val predicate: (String) -> Boolean = { it -> it.isNotEmpty() }) :
            FilterList<String> {
            override fun filter(input: List<String>) = input.filter(predicate = predicate).last()
        }
    }

    interface Concatenation<secVal> {
        fun concat(secondValue: secVal): String

        class Base(private val baseUrl: String) : Concatenation<Int> {
            override fun concat(secondValue: Int) = "$baseUrl$secondValue"
        }

    }

    interface PrefixRemoving {

        fun removePrefix(input: String): String

        class Base(private val prefix: String) : PrefixRemoving {
            override fun removePrefix(input: String) = input.removePrefix(prefix)
        }

    }
}