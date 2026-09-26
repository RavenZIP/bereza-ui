package com.github.ravenzip.berezaUI.core.components.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
private fun <T> RadioGroupImpl(
    source: SnapshotStateList<T>,
    onSelectionItemChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    selectedKey: Any? = null,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    content: @Composable RowScope.(T) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = contentPadding,
    ) {
        source.forEach { item ->
            val itemKey = remember(key, item) { key(item) }

            key(itemKey) {
                RadioButton(
                    selected = selectedKey == itemKey,
                    onClick = { onSelectionItemChange(item) },
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

@Composable
fun <T> RadioGroup(
    source: SnapshotStateList<T>,
    selectedItem: T,
    onSelectionItemChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    content: @Composable RowScope.(T) -> Unit,
) {
    val selectedKey = remember(selectedItem) { key(selectedItem) }

    RadioGroupImpl(
        source = source,
        onSelectionItemChange = onSelectionItemChange,
        modifier = modifier,
        selectedKey = selectedKey,
        key = key,
        enabled = enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
        content = content,
    )
}

@Composable
fun <T> RadioGroup(
    source: SnapshotStateList<T>,
    selectedItem: T?,
    onSelectionItemChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    content: @Composable RowScope.(T) -> Unit,
) {
    val selectedKey =
        remember(selectedItem) { if (selectedItem != null) key(selectedItem) else null }

    RadioGroupImpl(
        source = source,
        onSelectionItemChange = onSelectionItemChange,
        modifier = modifier,
        selectedKey = selectedKey,
        key = key,
        enabled = enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
        content = content,
    )
}
