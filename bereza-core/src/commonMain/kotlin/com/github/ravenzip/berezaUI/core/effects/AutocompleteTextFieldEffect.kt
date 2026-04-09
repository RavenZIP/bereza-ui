package com.github.ravenzip.berezaUI.core.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.github.ravenzip.berezaUI.core.data.AutocompleteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

internal fun <T> Collection<T>.startWith(
    value: String,
    sourceItemToString: (T) -> String,
): List<T> {
    val loverCaseValue = value.lowercase()
    return filter { item -> sourceItemToString(item).lowercase().contains(loverCaseValue) }
}

//
// internal fun <F, S> snapshotCombinedFlow(first: () -> F, second: () -> S) =
//    snapshotFlow { first() }.combine(snapshotFlow { second() }) { f, s -> Pair(f, s) }

internal fun <F, S> snapshotCombinedFlow(first: () -> F, second: () -> S): Flow<Pair<F, S>> {
    val firstFlow = snapshotFlow { first() }
    val secondFlow = snapshotFlow { second() }

    return firstFlow.combine(secondFlow) { f, s -> Pair(f, s) }
}

@Composable
internal fun ExpandedChangeEffect(expanded: () -> Boolean, onExpandedChange: (Boolean) -> Unit) {
    LaunchedEffect(expanded) {
        snapshotFlow { expanded() }.onEach { x -> onExpandedChange(x) }.launchIn(this)
    }
}

// TODO Учесть вероятную ошибку в случае падения запроса по ветке ByQuery. Возможно, стоит сделать
// еще один callback, либо все три случая (начало, успешный поиск и неудачный) объединить в один
// sealed class
@Composable
internal fun <T> LoadSearchResult(
    source: AutocompleteSource<T>,
    sourceItemToString: (T) -> String,
    searchQuery: () -> String,
    expanded: () -> Boolean,
    onSearchStarted: () -> Unit,
    onSearchFinished: (List<T>) -> Unit,
) {
    LaunchedEffect(source, sourceItemToString, searchQuery, expanded) {
        when (source) {
            is AutocompleteSource.Predefined -> {
                loadSearchResult(
                        source = source,
                        sourceItemToString = sourceItemToString,
                        expanded = expanded,
                        searchQuery = searchQuery,
                        onSearchStarted = onSearchStarted,
                        onSearchFinished = onSearchFinished,
                    )
                    .launchIn(this)
            }

            is AutocompleteSource.ByQuery -> {
                loadSearchResult(
                        source = source,
                        expanded = expanded,
                        searchQuery = searchQuery,
                        onSearchStarted = onSearchStarted,
                        onSearchFinished = onSearchFinished,
                    )
                    .launchIn(this)
            }
        }
    }
}

internal fun <T> loadSearchResult(
    source: AutocompleteSource.Predefined<T>,
    searchQuery: () -> String,
    expanded: () -> Boolean,
    sourceItemToString: (T) -> String,
    onSearchStarted: () -> Unit,
    onSearchFinished: (List<T>) -> Unit,
) =
    snapshotCombinedFlow(expanded, searchQuery)
        .onStart { onSearchStarted() }
        .map { x ->
            if (x.second.isEmpty()) {
                return@map source.items
            }

            if (source.search == null) {
                return@map source.items.startWith(x.second, sourceItemToString)
            }

            return@map source.search(source.items, x.second)
        }
        .onEach { items -> onSearchFinished(items) }

@OptIn(ExperimentalCoroutinesApi::class)
internal fun <T> loadSearchResult(
    source: AutocompleteSource.ByQuery<T>,
    searchQuery: () -> String,
    expanded: () -> Boolean,
    onSearchStarted: () -> Unit,
    onSearchFinished: (List<T>) -> Unit,
) =
    snapshotCombinedFlow(expanded, searchQuery)
        .onEach { onSearchStarted() }
        .flatMapLatest { x -> source.query(x.second) }
        .onEach { response -> onSearchFinished(response) }
