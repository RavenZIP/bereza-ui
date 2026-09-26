package com.github.ravenzip.bereza.core.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.github.ravenzip.bereza.core.data.SourceState
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

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
