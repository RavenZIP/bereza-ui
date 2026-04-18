package com.github.ravenzip.berezaUI.reactive.components.switch

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.switch.SwitchWithText
import com.github.ravenzip.berezaUI.extensions.components.SwitchWithText
import com.github.ravenzip.berezaUI.reactive.data.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun Switch(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    Switch(
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
fun SwitchWithText(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val state by control.collectAsComponentState()

    SwitchWithText(
        selected = state.value,
        enabled = state.enabled,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        text = text,
        padding = padding,
        colors = colors,
        shape = shape,
    )
}

@Composable
fun SwitchWithText(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle? = null,
    description: String? = null,
    descriptionStyle: TextStyle? = null,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val state by control.collectAsComponentState()

    SwitchWithText(
        selected = state.value,
        enabled = state.enabled,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        label = label,
        labelStyle = labelStyle,
        description = description,
        descriptionStyle = descriptionStyle,
        padding = padding,
        colors = colors,
        shape = shape,
    )
}
