package com.github.ravenzip.berezaUI.reactive.components.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.extensions.components.radio.RadioGroup
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun <T, K : Any> RadioGroup(
    control: MutableFormControl<T>,
    source: List<T>,
    keySelector: (T) -> K,
    text: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val value = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

    RadioGroup(
        source = source,
        selectedItem = value,
        onSelectedItemChange = { value ->
            control.setValue(value)
            control.markAsDirty()
        },
        keySelector = keySelector,
        text = text,
        enabled = status.isEnabled(),
        modifier = modifier,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T, K : Any> RadioGroup(
    control: MutableFormControl<T>,
    source: List<T>,
    keySelector: (T) -> K,
    displayedText: (T) -> String,
    displayedTextStyle: TextStyle =
        TextStyle.Default.merge(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    modifier: Modifier = Modifier,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val value = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

    RadioGroup(
        source = source,
        selectedItem = value,
        onSelectedItemChange = { value ->
            control.setValue(value)
            control.markAsDirty()
        },
        keySelector = keySelector,
        displayedText = displayedText,
        displayedTextStyle = displayedTextStyle,
        enabled = status.isEnabled(),
        modifier = modifier,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
