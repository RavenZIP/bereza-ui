package com.github.ravenzip.berezaUI.core.data.autocomplete

import com.github.ravenzip.berezaUI.core.effects.snapshotCombinedFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class AutocompleteState<T>(val source: AutocompleteSource<T>) {
    private val _expanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _result: MutableStateFlow<List<T>> = MutableStateFlow<List<T>>(emptyList())
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val expanded: StateFlow<Boolean> = _expanded.asStateFlow()
    val result: StateFlow<List<T>> = _result.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun setExpanded(value: Boolean) = _expanded.update { value }

    private fun onStart() {
        _isLoading.value = true
        _result.update { emptyList() }
    }

    private fun onFinish(result: List<T>) {
        _isLoading.value = false
        _result.update { result }
    }

    // TODO Учесть вероятную ошибку в случае падения запроса по ветке ByQuery. Возможно, стоит
    // сделать
    // еще один callback, либо все три случая (начало, успешный поиск и неудачный) объединить в один
    // sealed class
    fun search(searchQuery: () -> String) =
        when (source) {
            is AutocompleteSource.Predefined ->
                search(
                    source = source,
                    searchQuery = searchQuery,
                    expanded = { _expanded.value },
                    onSearchStarted = { onStart() },
                    onSearchFinished = { x -> onFinish(x) },
                )

            is AutocompleteSource.ByQuery ->
                search(
                    source = source,
                    searchQuery = searchQuery,
                    expanded = { _expanded.value },
                    onSearchStarted = { onStart() },
                    onSearchFinished = { x -> onFinish(x) },
                )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T> search(
        source: AutocompleteSource.Predefined<T>,
        searchQuery: () -> String,
        expanded: () -> Boolean,
        onSearchStarted: () -> Unit,
        onSearchFinished: (List<T>) -> Unit,
    ) =
        snapshotCombinedFlow(expanded, searchQuery)
            .onEach { onSearchStarted() }
            .map { x ->
                if (x.second.isEmpty()) source.items else source.search(source.items, x.second)
            }
            .onEach { items -> onSearchFinished(items) }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T> search(
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
}
