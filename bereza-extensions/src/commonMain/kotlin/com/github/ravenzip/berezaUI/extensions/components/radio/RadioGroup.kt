package com.github.ravenzip.berezaUI.extensions.components.radio

import androidx.compose.foundation.layout.Arrangement
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
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup

@Composable
fun <T, K : Any> RadioGroup(
    source: List<T>,
    selectedItem: T,
    onSelectedItemChange: (T) -> Unit,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    displayedText: (T) -> String,
    displayedTextStyle: TextStyle =
        TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    enabled: Boolean = true,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    RadioGroup(
        source = source,
        selectedItem = selectedItem,
        onSelectedItemChange = onSelectedItemChange,
        keySelector = keySelector,
        modifier = modifier,
        text = { item -> Text(text = displayedText(item), style = displayedTextStyle) },
        enabled = enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
