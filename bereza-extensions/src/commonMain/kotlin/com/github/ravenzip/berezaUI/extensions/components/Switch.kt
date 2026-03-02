package com.github.ravenzip.berezaUI.extensions.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.core.components.switch.SwitchWithText
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun SwitchWithText(
    isSelected: Boolean,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle? = null,
    description: String? = null,
    descriptionStyle: TextStyle? = null,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val calculatedLabelStyle =
        labelStyle
            ?: if (description != null)
                TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            else TextStyle.Default.merge(fontSize = 18.sp)

    SwitchWithText(
        isSelected = isSelected,
        isEnabled = isEnabled,
        onClick = onClick,
        modifier = modifier,
        text = {
            LabelWithOptionalDescription(
                label = label,
                labelStyle = calculatedLabelStyle,
                description = description,
                descriptionStyle = descriptionStyle,
            )
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
    labelStyle: TextStyle? = null,
    description: String? = null,
    descriptionStyle: TextStyle? = null,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val calculatedLabelStyle =
        labelStyle
            ?: if (description != null)
                TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            else TextStyle.Default.merge(fontSize = 18.sp)

    SwitchWithText(
        control = control,
        modifier = modifier,
        text = {
            LabelWithOptionalDescription(
                label = label,
                labelStyle = calculatedLabelStyle,
                description = description,
                descriptionStyle = descriptionStyle,
            )
        },
        padding = padding,
        colors = colors,
        shape = shape,
    )
}
