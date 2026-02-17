package components.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Собирает значения из потока типа List<T> и представляет его последнее значение через
 * [SnapshotStateList]
 *
 * @param initialValue начальное состояние
 */
@Composable
fun <T> Flow<List<T>>.collectAsSnapshotStateList(
    initialValue: List<T> = emptyList()
): SnapshotStateList<T> {
    val result = remember { mutableStateListOf<T>().apply { addAll(initialValue) } }

    LaunchedEffect(initialValue, this) {
        this@collectAsSnapshotStateList.collect { x ->
            result.clear()
            result.addAll(x)
        }
    }

    return result
}

/**
 * Собирает значения из потока типа List<T> и представляет его последнее значение через
 * [SnapshotStateList]
 *
 * Отличается от [collectAsSnapshotStateList] тем, что берет начальное значение из потока
 */
@Composable
fun <T> StateFlow<List<T>>.collectAsSnapshotStateList(): SnapshotStateList<T> {
    return collectAsSnapshotStateList(this.value)
}
