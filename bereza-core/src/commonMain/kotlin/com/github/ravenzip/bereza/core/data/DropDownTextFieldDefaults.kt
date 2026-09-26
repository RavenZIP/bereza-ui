package com.github.ravenzip.bereza.core.data

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

@Immutable class DropDownMenuColors(val containerColor: Color, val borderColor: Color? = null)

@Immutable
object DropDownTextFieldDefaults {
    @Composable
    fun colors(): DropDownTextFieldColors = DropDownTextFieldColors(textFieldColors(), menuColors())

    @Composable
    fun outlinedColors(): DropDownTextFieldColors =
        DropDownTextFieldColors(outlinedTextFieldColors(), outlinedMenuColors())

    @Composable fun textFieldColors(): TextFieldColors = TextFieldDefaults.colors()

    @Composable fun outlinedTextFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors()

    @Composable fun menuColors(): DropDownMenuColors = DropDownMenuDefaults.colors()

    @Composable fun outlinedMenuColors(): DropDownMenuColors = DropDownMenuDefaults.outlinedColors()
}

// TODO разобраться с цветами
@Immutable
object DropDownMenuDefaults {
    @Composable
    fun colors(): DropDownMenuColors =
        DropDownMenuColors(containerColor = MaterialTheme.colorScheme.surface)

    @Composable
    fun outlinedColors(): DropDownMenuColors =
        DropDownMenuColors(
            containerColor = MaterialTheme.colorScheme.surface,
            borderColor = OutlinedTextFieldDefaults.colors().focusedLabelColor,
        )
}
