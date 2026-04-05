package com.github.ravenzip.berezaUI.core.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.Flow

sealed class DropDownTextFieldSource<T> {
    class Predefined<T>(
        val items: SnapshotStateList<T>,
        val search: ((List<T>, String) -> List<T>)? = null,
    ) : DropDownTextFieldSource<T>()

    class ByQuery<T>(val query: (String) -> Flow<List<T>>) : DropDownTextFieldSource<T>()
}
