package com.github.ravenzip.bereza.core.components.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.data.SelectionChange

@Composable
fun <T> CheckboxGroup(
    source: List<T>,
    selectedItems: List<T>,
    onSelectionItemChange: (T, SelectionChange) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: CheckboxColors = CheckboxDefaults.colors(),
    content: @Composable RowScope.(T) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = contentPadding,
    ) {
        source.forEach { item ->
            val itemKey = remember(key, item) { key(item) }
            val selected = selectedItems.any { key(it) == itemKey }

            key(itemKey) {
                Checkbox(
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
