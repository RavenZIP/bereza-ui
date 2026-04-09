package com.github.ravenzip.berezaUI.core.data.autocomplete

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class AutocompleteTextFieldColors(
    val textFieldColors: TextFieldColors,
    val menuColors: AutocompleteMenuColors,
)

@Immutable
object AutocompleteTextFieldDefaults {
    @Composable
    fun colors(): AutocompleteTextFieldColors =
        AutocompleteTextFieldColors(textFieldColors(), menuColors())

    @Composable fun textFieldColors(): TextFieldColors = TextFieldDefaults.colors()

    @Composable fun menuColors(): AutocompleteMenuColors = AutocompleteMenuDefaults.colors()
}

@Immutable
object OutlinedAutocompleteTextFieldDefaults {
    @Composable
    fun colors(): AutocompleteTextFieldColors =
        AutocompleteTextFieldColors(textFieldColors(), menuColors())

    @Composable fun textFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors()

    @Composable fun menuColors(): AutocompleteMenuColors = OutlinedAutocompleteMenuDefaults.colors()
}

@Immutable class AutocompleteMenuColors(val containerColor: Color, val borderColor: Color? = null)

@Immutable
object OutlinedAutocompleteMenuDefaults {
    @Composable
    fun colors(): AutocompleteMenuColors =
        AutocompleteMenuColors(
            containerColor = MaterialTheme.colorScheme.surface,
            borderColor = OutlinedTextFieldDefaults.colors().focusedLabelColor,
        )
}

object AutocompleteMenuDefaults {
    @Composable
    fun colors(): AutocompleteMenuColors =
        AutocompleteMenuColors(containerColor = MaterialTheme.colorScheme.surface)
}
