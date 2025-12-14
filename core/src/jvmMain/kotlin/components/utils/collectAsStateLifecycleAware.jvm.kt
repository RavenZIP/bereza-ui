package components.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

@Composable
internal actual fun <T> StateFlow<T>.collectAsStateLifecycleAware(
    context: CoroutineContext
): State<T> = this.collectAsState(context)

@Composable
internal actual fun <T> Flow<T>.collectAsStateLifecycleAware(
    initialValue: T,
    context: CoroutineContext,
): State<T> = this.collectAsState(initialValue, context)
