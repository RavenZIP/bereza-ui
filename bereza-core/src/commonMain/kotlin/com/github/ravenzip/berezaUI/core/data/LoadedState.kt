package com.github.ravenzip.berezaUI.core.data

sealed class LoadedState<T> {
    object Loading : LoadedState<Nothing>()

    object Empty : LoadedState<Nothing>()

    data class Data<T>(val source: List<T>) : LoadedState<T>()
}