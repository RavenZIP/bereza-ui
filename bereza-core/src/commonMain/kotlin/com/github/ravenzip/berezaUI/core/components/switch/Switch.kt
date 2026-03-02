package com.github.ravenzip.berezaUI.core.components.switch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun Switch(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val isSelected = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

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
    isSelected: Boolean,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    Row(
        modifier = modifier.clip(shape).clickable { onClick() }.padding(padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        text()

        Spacer(modifier = Modifier.weight(1f))

        Switch(checked = isSelected, onCheckedChange = null, enabled = isEnabled, colors = colors)
    }
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
    val isSelected = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

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
