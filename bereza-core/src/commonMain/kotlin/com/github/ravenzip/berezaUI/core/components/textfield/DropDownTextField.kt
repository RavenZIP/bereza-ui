package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState

// TODO сделать вариацию с FormControl
// TODO подумать над названием параметров
// TODO отсутствует поиск среди элементов по строке
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownTextField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchResults: SnapshotStateList<T>,
    onSelectItem: (T) -> Unit,
    isEnabled: Boolean = true,
    isReadonly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onExpandedChange: (Boolean) -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItem: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val expanded = rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { x ->
            expanded.value = x
            onExpandedChange(x)
        },
    ) {
        OutlinedSingleLineTextField(
            value = searchQuery,
            onValueChange = { x -> onSearchQueryChange(x) },
            modifier =
                modifier.menuAnchor(
                    if (isReadonly) ExposedDropdownMenuAnchorType.PrimaryNotEditable
                    else ExposedDropdownMenuAnchorType.PrimaryEditable
                ),
            isEnabled = isEnabled,
            isReadonly = isReadonly,
            errorState = errorState,
            onFocusChange = onFocusChange,
            label = label,
            trailingIcon = trailingIcon,
            shape = shape,
            colors = colors,
        )

        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                onExpandedChange(expanded.value)
            },
            modifier =
                Modifier.border(
                    2.dp,
                    color = colors.focusedLabelColor,
                    shape = RoundedCornerShape(12.dp),
                ),
            shape = RoundedCornerShape(12.dp),
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            if (searchResults.isNotEmpty()) {
                searchResults.forEach { item ->
                    DropdownMenuItem(
                        text = { dropDownMenuItem(item) },
                        onClick = {
                            onSelectItem(item)
                            expanded.value = false
                        },
                        enabled = isEnabled,
                    )
                }
            } else {
                DropdownMenuItem(text = dropDownMenuItemPlaceholder, onClick = {}, enabled = false)
            }
        }
    }
}
