package com.github.ravenzip.berezaUI.core.data

sealed class ComponentErrorState {
    data object Ok : com.github.ravenzip.berezaUI.core.data.ComponentErrorState()

    data class Error(val message: String) :
        com.github.ravenzip.berezaUI.core.data.ComponentErrorState()
}

fun com.github.ravenzip.berezaUI.core.data.ComponentErrorState.unwrapErrorMessage(): String =
    when (this) {
        is com.github.ravenzip.berezaUI.core.data.ComponentErrorState.Error -> this.message
        else -> ""
    }
