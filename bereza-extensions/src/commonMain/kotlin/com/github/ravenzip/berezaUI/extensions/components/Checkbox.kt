package com.github.ravenzip.berezaUI.extensions.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.core.components.checkbox.CheckboxWithText

@Composable
fun CheckboxWithText(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle? = null,
    description: String? = null,
    descriptionStyle: TextStyle? = null,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val calculatedLabelStyle =
        labelStyle
            ?: if (description != null)
                TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            else TextStyle.Default.merge(fontSize = 18.sp)

    CheckboxWithText(
        selected = selected,
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
        enabled = enabled,
        colors = colors,
        padding = padding,
        shape = shape,
    )
}
