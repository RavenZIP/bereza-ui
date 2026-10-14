package com.github.ravenzip.bereza.core.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.github.ravenzip.bereza.core.data.SelectionChange
import com.github.ravenzip.bereza.core.data.SourceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class MultiSelectChange<T>(
    val selected: List<T>,
    val item: T,
    val kind: SelectionChange,
)

@Composable
internal fun <T> rememberFilteredSource(
    source: List<T>,
    inputText: String,
    search: (T, String) -> Boolean,
): List<T> {
    return remember(source, inputText, search) {
        source.filter { item ->
            search(item, inputText)
        }
    }
}

internal fun <T> toggleItem(selected: List<T>, item: T, key: (T) -> Any?): MultiSelectChange<T> {
    val itemKey = key(item)
    val selectedIndex = selected.indexOfFirst { iterableItem ->
        key(iterableItem) == itemKey
    }
    val isSelected = selectedIndex >= 0

    val newSelected =
        selected.toMutableList().apply {
            if (isSelected) {
                removeAt(selectedIndex)
            } else {
                add(item)
            }
        }

    return MultiSelectChange(
        newSelected,
        item,
        if (isSelected) SelectionChange.Deselect else SelectionChange.Select,
    )
}

internal fun <T> removeItem(selected: List<T>, item: T, key: (T) -> Any?): MultiSelectChange<T> {
    val itemKey = key(item)
    val selectedIndex = selected.indexOfFirst { iterableItem ->
        key(iterableItem) == itemKey
    }

    val newSelected = selected.toMutableList().apply { removeAt(selectedIndex) }

    return MultiSelectChange(
        newSelected,
        item,
        SelectionChange.Deselect,
    )
}

@Composable
fun <T> ComputeInputText(
    selected: T? = null,
    displayWith: (T) -> String,
    onInputTextChange: (String) -> Unit,
) {
    LaunchedEffect(displayWith, selected) {
        val text = if (selected != null) displayWith(selected) else ""
        onInputTextChange(text)
    }
}

@Composable
fun <T> Search(
    inputText: String,
    expanded: Boolean,
    search: (String) -> Flow<List<T>>,
    searchDebounce: Duration = 500.milliseconds,
    onSourceStateChange: (SourceState<T>) -> Unit,
) {
    LaunchedEffect(inputText, expanded, search) {
        if (!expanded) return@LaunchedEffect

        onSourceStateChange(SourceState.Loading)

        delay(searchDebounce)

        search(inputText).collect { response ->
            onSourceStateChange(SourceState.Content(response))
        }
    }
}
