package com.github.ravenzip.berezaUI.reactive.components.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.checkbox.CheckboxGroup
import com.github.ravenzip.berezaUI.reactive.data.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun <T, K : Any> CheckboxGroup(
    control: MutableFormControl<List<T>>,
    source: List<T>,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    text: @Composable (T) -> Unit,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    CheckboxGroup(
        source = source,
        selectedItems = state.value,
        onSelectedItemChange = { selectedItem ->
            val selectedItemKey = keySelector(selectedItem)
            val selected = state.value.any { item -> keySelector(item) == selectedItemKey }
            val newSelectedItems =
                if (selected) state.value.filterNot { item -> keySelector(item) == selectedItemKey }
                else state.value + selectedItem

            control.setValue(newSelectedItems)
            control.markAsTouched()
        },
        keySelector = keySelector,
        modifier = modifier,
        text = text,
        enabled = state.enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
