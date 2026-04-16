package com.github.ravenzip.berezaUI.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Собирает значения из [StateFlow] и предоставляет последнее значение как [State]
 *
 * Во всех платформах, кроме Android, будет использован collectAsState
 *
 * В случае Android платформы будет использован collectAsStateWithLifecycle
 */
@Composable
expect fun <T> StateFlow<T>.collectAsStateLifecycleAware(
    context: CoroutineContext = EmptyCoroutineContext
): State<T>

@Composable
expect fun <T> Flow<T>.collectAsStateLifecycleAware(
    initialValue: T,
    context: CoroutineContext = EmptyCoroutineContext,
): State<T>
