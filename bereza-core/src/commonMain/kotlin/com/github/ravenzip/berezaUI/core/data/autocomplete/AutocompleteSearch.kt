package com.github.ravenzip.berezaUI.core.data.autocomplete

object AutocompleteSearch {
    fun <T> startWith(source: List<T>, value: String, key: (T) -> String): List<T> =
        source.filter { item ->
            key(item).startsWith(prefix = value, ignoreCase = true)
        }

    fun <T> contains(source: List<T>, value: String, key: (T) -> String): List<T> =
        source.filter { item ->
            key(item).contains(other = value, ignoreCase = true)
        }
}
