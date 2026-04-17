package com.github.ravenzip.berezaUI.reactive.components.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.switch.SwitchGroup
import com.github.ravenzip.berezaUI.core.utils.collectAsStateLifecycleAware
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun <T, K : Any> SwitchGroup(
    control: MutableFormControl<List<T>>,
    source: List<T>,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    text: @Composable (T) -> Unit,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val selectedItems by control.valueChanges.collectAsStateLifecycleAware()
    val status by control.statusChanges.collectAsStateLifecycleAware()

    SwitchGroup(
        source = source,
        selectedItems = selectedItems,
        onSelectedItemChange = { selectedItem ->
            val selectedItemKey = keySelector(selectedItem)
            val selected = selectedItems.any { item -> keySelector(item) == selectedItemKey }
            val newSelectedItems =
                if (selected)
                    selectedItems.filterNot { item -> keySelector(item) == selectedItemKey }
                else selectedItems + selectedItem

            control.setValue(newSelectedItems)
            control.markAsTouched()
        },
        keySelector = keySelector,
        modifier = modifier,
        text = text,
        enabled = status.isEnabled(),
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
