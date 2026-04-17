package com.github.ravenzip.berezaUI.core.components.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun <T, K : Any> RadioGroup(
    source: List<T>,
    selectedItem: T,
    onSelectedItemChange: (T) -> Unit,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    text: @Composable (T) -> Unit,
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val selectedKey = remember(selectedItem) { keySelector(selectedItem) }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = contentPadding,
        userScrollEnabled = false,
    ) {
        items(source, key = keySelector) { item ->
            val itemKey = keySelector(item)

            RadioButtonWithText(
                isSelected = selectedKey == itemKey,
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
