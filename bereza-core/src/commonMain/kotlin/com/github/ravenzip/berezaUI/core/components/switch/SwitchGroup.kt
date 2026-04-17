package com.github.ravenzip.berezaUI.core.components.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
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

    LazyColumn(
        modifier = modifier,
        verticalArrangement = contentPadding,
        userScrollEnabled = false,
    ) {
        items(source, key = keySelector) { item ->
            val itemKey = keySelector(item)
            val isSelected = selectedKeys.contains(itemKey)

            SwitchWithText(
                isSelected = isSelected,
                onClick = { onSelectedItemChange(item) },
                text = { text(item) },
                isEnabled = enabled,
                padding = padding,
                shape = shape,
                colors = colors,
            )
        }
    }
}
