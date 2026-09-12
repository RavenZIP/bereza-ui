package com.github.ravenzip.berezaUI.core.components.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
fun <T> RadioGroup(
    source: SnapshotStateList<T>,
    selectedItem: T,
    onSelectionItemChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    text: @Composable (T) -> Unit,
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val selectedKey = remember(selectedItem) { key(selectedItem) }

    Column(
        modifier = modifier,
        verticalArrangement = contentPadding,
    ) {
        source.forEach { item ->
            val itemKey = key(item)

            key(itemKey) {
                RadioButtonWithText(
                    selected = selectedKey == itemKey,
                    onClick = { onSelectionItemChange(item) },
                    text = { text(item) },
                    enabled = enabled,
                    padding = padding,
                    shape = shape,
                    colors = colors,
                )
            }
        }
    }
}
