package data

sealed class ComponentErrorState {
    data object Ok : ComponentErrorState()

    data class Error(val message: String) : ComponentErrorState()
}

fun ComponentErrorState.unwrapErrorMessage(): String =
    when (this) {
        is ComponentErrorState.Error -> this.message
        else -> ""
    }
