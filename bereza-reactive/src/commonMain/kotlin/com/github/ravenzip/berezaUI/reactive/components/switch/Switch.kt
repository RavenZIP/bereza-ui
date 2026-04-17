package com.github.ravenzip.berezaUI.reactive.components.switch

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.switch.SwitchWithText
import com.github.ravenzip.berezaUI.core.utils.collectAsStateLifecycleAware
import com.github.ravenzip.berezaUI.extensions.components.SwitchWithText
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun Switch(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val isSelected = control.valueChanges.collectAsStateLifecycleAware().value
    val status = control.statusChanges.collectAsStateLifecycleAware().value

    Switch(
        checked = isSelected,
        onCheckedChange = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = status.isEnabled(),
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
    val isSelected = control.valueChanges.collectAsStateLifecycleAware().value
    val status = control.statusChanges.collectAsStateLifecycleAware().value

    SwitchWithText(
        isSelected = isSelected,
        isEnabled = status.isEnabled(),
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
    val isSelected = control.valueChanges.collectAsStateLifecycleAware().value
    val status = control.statusChanges.collectAsStateLifecycleAware().value

    SwitchWithText(
        isSelected = isSelected,
        isEnabled = status.isEnabled(),
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
