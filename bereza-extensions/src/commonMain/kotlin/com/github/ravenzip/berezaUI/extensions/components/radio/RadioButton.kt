package com.github.ravenzip.berezaUI.extensions.components.radio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.core.components.radio.RadioButtonWithText

@Composable
fun RadioButtonWithText(
    isSelected: Boolean,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    text: String,
    textStyle: TextStyle =
        TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    RadioButtonWithText(
        isSelected = isSelected,
        isEnabled = isEnabled,
        onClick = onClick,
        modifier = modifier,
        text = { Text(text = text, style = textStyle) },
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
