package com.github.ravenzip.berezaUI.core.data.autocomplete

object AutocompleteSearch {
    fun <T> startWith(source: List<T>, value: String, key: (T) -> String): List<T> {
        val lowerCaseValue = value.lowercase()
        return source.filter { item -> key(item).lowercase().startsWith(lowerCaseValue) }
    }

    fun <T> contains(source: List<T>, value: String, key: (T) -> String): List<T> {
        val lowerCaseValue = value.lowercase()
        return source.filter { item -> key(item).lowercase().contains(lowerCaseValue) }
    }
}
