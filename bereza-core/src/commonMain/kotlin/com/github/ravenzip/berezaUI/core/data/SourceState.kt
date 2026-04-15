package com.github.ravenzip.berezaUI.core.data

sealed class SourceState<T> {
    object Loading : SourceState<Nothing>()

    object Empty : SourceState<Nothing>()

    data class Content<T>(val items: List<T>) : SourceState<T>()
}
