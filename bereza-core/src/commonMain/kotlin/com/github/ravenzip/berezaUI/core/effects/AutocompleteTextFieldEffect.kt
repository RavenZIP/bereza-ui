package com.github.ravenzip.berezaUI.core.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.github.ravenzip.berezaUI.core.data.autocomplete.AutocompleteState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

internal fun <F, S> snapshotCombinedFlow(first: () -> F, second: () -> S): Flow<Pair<F, S>> {
    val firstFlow = snapshotFlow { first() }
    val secondFlow = snapshotFlow { second() }

    return firstFlow.combine(secondFlow) { f, s -> Pair(f, s) }
}

@Composable
internal fun <T> SearchEffect(state: AutocompleteState<T>, searchQuery: () -> String) {
    LaunchedEffect(state, searchQuery) { state.search(searchQuery).launchIn(this) }
}
