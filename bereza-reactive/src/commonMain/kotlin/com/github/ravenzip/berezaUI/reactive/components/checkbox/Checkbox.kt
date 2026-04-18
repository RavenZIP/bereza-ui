package com.github.ravenzip.berezaUI.reactive.components.checkbox

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.checkbox.CheckboxWithText
import com.github.ravenzip.berezaUI.extensions.components.CheckboxWithText
import com.github.ravenzip.berezaUI.reactive.data.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun Checkbox(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    Checkbox(
        checked = state.value,
        onCheckedChange = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        colors = colors,
    )
}

@Composable
fun CheckboxWithText(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val state by control.collectAsComponentState()

    CheckboxWithText(
        selected = state.value,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        text = text,
        enabled = state.enabled,
        colors = colors,
        padding = padding,
        shape = shape,
    )
}

@Composable
fun CheckboxWithText(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle? = null,
    description: String? = null,
    descriptionStyle: TextStyle? = null,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val state by control.collectAsComponentState()

    CheckboxWithText(
        selected = state.value,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        label = label,
        labelStyle = labelStyle,
        description = description,
        descriptionStyle = descriptionStyle,
        enabled = state.enabled,
        colors = colors,
        padding = padding,
        shape = shape,
    )
}
