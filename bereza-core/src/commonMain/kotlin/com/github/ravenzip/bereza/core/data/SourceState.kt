package com.github.ravenzip.bereza.core.data

sealed class SourceState<out T> {
    object Loading : SourceState<Nothing>()

    data class Content<T>(val items: List<T>) : SourceState<T>()
}
