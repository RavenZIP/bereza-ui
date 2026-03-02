package com.github.ravenzip.berezaUI.extensions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.core.components.switch.SwitchWithText
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun SwitchWithText(
    isSelected: Boolean,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle =
        TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    description: String? = null,
    descriptionStyle: TextStyle? = null,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    SwitchWithText(
        isSelected = isSelected,
        isEnabled = isEnabled,
        onClick = onClick,
        modifier = modifier,
        text = {
            if (description != null && descriptionStyle != null) {
                Column {
                    Text(text = label, style = labelStyle)

                    Text(text = description, style = descriptionStyle)
                }
            } else {
                Text(text = label, style = labelStyle)
            }
        },
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
    labelStyle: TextStyle =
        TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    description: String? = null,
    descriptionStyle: TextStyle? = null,
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
        label = label,
        labelStyle = labelStyle,
        description = description,
        descriptionStyle = descriptionStyle,
        padding = padding,
        colors = colors,
        shape = shape,
    )
}
