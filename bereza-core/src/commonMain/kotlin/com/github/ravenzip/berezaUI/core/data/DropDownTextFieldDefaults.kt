package com.github.ravenzip.berezaUI.core.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class DropDownTextFieldColors(
    val textFieldColors: TextFieldColors,
    val menuColors: DropDownMenuColors,
)

@Immutable
object DropDownTextFieldDefaults {
    @Composable
    fun colors(): DropDownTextFieldColors = DropDownTextFieldColors(textFieldColors(), menuColors())

    @Composable fun textFieldColors(): TextFieldColors = TextFieldDefaults.colors()

    @Composable fun menuColors(): DropDownMenuColors = DropDownMenuDefaults.colors()
}

@Immutable
object OutlinedDropDownTextFieldDefaults {
    @Composable
    fun colors(): DropDownTextFieldColors = DropDownTextFieldColors(textFieldColors(), menuColors())

    @Composable fun textFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors()

    @Composable fun menuColors(): DropDownMenuColors = OutlinedDropDownMenuDefaults.colors()
}

@Immutable class DropDownMenuColors(val containerColor: Color, val borderColor: Color? = null)

@Immutable
object OutlinedDropDownMenuDefaults {
    @Composable
    fun colors(): DropDownMenuColors =
        DropDownMenuColors(
            containerColor = MaterialTheme.colorScheme.surface,
            borderColor = OutlinedTextFieldDefaults.colors().focusedLabelColor,
        )
}

@Immutable
object DropDownMenuDefaults {
    @Composable
    fun colors(): DropDownMenuColors =
        DropDownMenuColors(containerColor = MaterialTheme.colorScheme.surface)
}
