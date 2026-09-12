package com.github.ravenzip.berezaUI.core.components.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.data.SelectionChange

@Composable
fun <T> SwitchGroup(
    source: SnapshotStateList<T>,
    selectedItems: SnapshotStateList<T>,
    onSelectionItemChange: (T, SelectionChange) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    content: @Composable ColumnScope.(T) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = contentPadding,
    ) {
        source.forEach { item ->
            val itemKey = remember(key) { key(item) }
            val selected = selectedItems.any { key(it) == itemKey }

            key(itemKey) {
                Switch(
                    selected = selected,
                    onClick = {
                        onSelectionItemChange(
                            item,
                            if (selected) SelectionChange.Deselect else SelectionChange.Select,
                        )
                    },
                    enabled = enabled,
                    padding = padding,
                    shape = shape,
                    colors = colors,
                ) {
                    content(item)
                }
            }
        }
    }
}
