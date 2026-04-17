package com.github.ravenzip.berezaUI.core.components.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun <T, K : Any> BasicColumnGroup(
    source: List<T>,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    content: @Composable (K) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = contentPadding) {
        source.forEach { item ->
            val itemKey = keySelector(item)

            key(itemKey) { content(itemKey) }
        }
    }
}

@Composable
fun <T, K : Any> CheckboxGroup(
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
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    val sourceMap = remember(source) { source.associateBy(keySelector) }
    val selectedItemsMap = remember(selectedItems) { selectedItems.associateBy(keySelector) }

    BasicColumnGroup(
        source = source,
        keySelector = keySelector,
        modifier = modifier,
        contentPadding = contentPadding,
    ) { itemKey ->
        val isSelected = selectedItemsMap.contains(itemKey)
        val item = sourceMap.getValue(itemKey)

        CheckboxWithText(
            isSelected = isSelected,
            onClick = { onSelectedItemChange(item) },
            text = { text(item) },
            enabled = enabled,
            padding = padding,
            shape = shape,
            colors = colors,
        )
    }
}
