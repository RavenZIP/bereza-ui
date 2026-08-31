package com.github.ravenzip.berezaUI.core.components.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun <T, K : Any> SwitchGroup(
    source: List<T>,
    selectedItems: List<T>,
    onSelectedItemChange: (T) -> Unit,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    text: @Composable (T) -> Unit,
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val selectedKeys = remember(selectedItems) { selectedItems.map(keySelector).toSet() }

    Column(
        modifier = modifier,
        verticalArrangement = contentPadding,
    ) {
        source.forEach { item ->
            val itemKey = keySelector(item)

            key(itemKey) {
                SwitchWithText(
                    selected = itemKey in selectedKeys,
                    onClick = { onSelectedItemChange(item) },
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
