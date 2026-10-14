package com.github.ravenzip.bereza.core.components.switch

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.MultiSelectChange
import com.github.ravenzip.bereza.core.components.textfield.toggleItem

@Composable
fun <T> SwitchGroup(
    source: List<T>,
    selectedItems: List<T>,
    onSelectionItemChange: (MultiSelectChange<T>) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.(T) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = contentPadding,
    ) {
        source.forEach { item ->
            val itemKey = key(item)
            val selected = selectedItems.any { key(it) == itemKey }

            key(itemKey) {
                Switch(
                    checked = selected,
                    onCheckedChange = {
                        onSelectionItemChange(toggleItem(selected = selectedItems, item, key = key))
                    },
                    enabled = enabled,
                    padding = padding,
                    shape = shape,
                    colors = colors,
                    interactionSource = interactionSource,
                ) {
                    content(item)
                }
            }
        }
    }
}
