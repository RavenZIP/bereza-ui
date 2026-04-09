package com.github.ravenzip.berezaUI.core.data.autocomplete

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.Flow

sealed class AutocompleteSource<T> {
    class Predefined<T>(val items: SnapshotStateList<T>, val search: (List<T>, String) -> List<T>) :
        AutocompleteSource<T>()

    class ByQuery<T>(val query: (String) -> Flow<List<T>>) : AutocompleteSource<T>()
}
