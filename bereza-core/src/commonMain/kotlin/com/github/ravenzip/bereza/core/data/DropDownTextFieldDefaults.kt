package com.github.ravenzip.bereza.core.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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

    @Composable
    fun <T> MenuItem(
        item: T,
        displayWith: (T) -> String,
    ) {
        val text = remember(item) { displayWith(item) }
        Text(text)
    }

    @Composable
    fun <T> SelectableMenuItem(
        item: T,
        selected: List<T>,
        displayWith: (T) -> String,
        key: (T) -> Any? = { it },
    ) {
        val itemKey = key(item)
        val selected = selected.any { key(it) == itemKey }
        val text = remember(item) { displayWith(item) }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Checkbox(selected, onCheckedChange = null)
            Text(text = text)
        }
    }
}
