package com.github.ravenzip.berezaUI.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshotFlow
import com.github.ravenzip.krex.function.pairwise
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun FocusLostEffect(isFocused: State<Boolean>, onFocusLost: () -> Unit) {
    LaunchedEffect(Unit) {
        snapshotFlow { isFocused.value }
            .pairwise()
            .filter { x -> !x.second }
            .onEach { onFocusLost() }
            .launchIn(this)
    }
}
