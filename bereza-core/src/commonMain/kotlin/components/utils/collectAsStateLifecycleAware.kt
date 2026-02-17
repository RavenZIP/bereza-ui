package components.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Собирает значения из [StateFlow] и предоставляет последнее значение как [State]
 *
 * Во всех платформах, кроме Android, будет использован collectAsState
 *
 * В случае Android платформы будет использован collectAsStateWithLifecycle
 */
@Composable
internal expect fun <T> StateFlow<T>.collectAsStateLifecycleAware(
    context: CoroutineContext = EmptyCoroutineContext
): State<T>

@Composable
internal expect fun <T> Flow<T>.collectAsStateLifecycleAware(
    initialValue: T,
    context: CoroutineContext = EmptyCoroutineContext,
): State<T>
