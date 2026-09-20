package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.AnimatedArrow
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.DropDownTextFieldBox
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.TrailingContent
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.DropDownExpandEvent.Companion.isExpanded
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldColors
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.berezaUI.core.data.SourceState

/**
 * Select — компонент с возможностью выбора элемента из заданного списка. Не поддерживает ввод
 * текста, фильтрацию списка или добавление собственных значений.
 */
// TODO сделать Outlined версию
// TODO подпрыгивает при первом открытии страницы с этим компонентом. Возможно, из-за иконки Arrow
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Select(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var expanded by remember { mutableStateOf(false) }
    val text =
        remember(displayWith, selected) { if (selected != null) displayWith(selected) else "" }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(source),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        textField = {
            TextFieldWithSupportingRow(
                value = text,
                onValueChange = {},
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = enabled,
                    ),
                enabled = enabled,
                readonly = true,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                maxLines = 1,
                singleLine = true,
                label = label,
                placeholder = placeholder,
                trailingIcon = { TrailingContent(selected, expanded, enabled, onClear) },
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val text = remember(item) { displayWith(item) }
            Text(text = text)
        },
        emptyContent = { Text(text = "Не найдено") },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedSelect(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.outlinedColors(),
) {
    var expanded by remember { mutableStateOf(false) }
    val text =
        remember(displayWith, selected) { if (selected != null) displayWith(selected) else "" }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(source),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        textField = {
            OutlinedTextFieldWithSupportingRow(
                value = text,
                onValueChange = {},
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = enabled,
                    ),
                enabled = enabled,
                readonly = true,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                maxLines = 1,
                singleLine = true,
                label = label,
                placeholder = placeholder,
                trailingIcon = { TrailingContent(selected, expanded, enabled, onClear) },
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val text = remember(item) { displayWith(item) }
            Text(text = text)
        },
        emptyContent = { Text(text = "Не найдено") },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiSelect(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    onSelect: (T) -> Unit,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var expanded by remember { mutableStateOf(false) }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(source),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        collapseAfterSelect = false,
        textField = {
            ChipTextFieldWithSupportingRow(
                value = "",
                onValueChange = {},
                chips = selected,
                displayWith = displayWith,
                onRemoveChip = onRemoveChip,
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = enabled,
                    ),
                readonly = true,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val itemKey = key(item)
            val selected = selected.any { key(it) == itemKey }
            val text = remember(item) { displayWith(item) }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Checkbox(selected, onCheckedChange = null)
                Text(text = text)
            }
        },
        emptyContent = { Text(text = "Не найдено") },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedMultiSelect(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    onSelect: (T) -> Unit,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.outlinedColors(),
) {
    var expanded by remember { mutableStateOf(false) }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(source),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        collapseAfterSelect = false,
        textField = {
            OutlinedChipTextFieldWithSupportingRow(
                value = "",
                onValueChange = {},
                chips = selected,
                displayWith = displayWith,
                onRemoveChip = onRemoveChip,
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = enabled,
                    ),
                readonly = true,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val itemKey = key(item)
            val selected = selected.any { key(it) == itemKey }
            val text = remember(item) { displayWith(item) }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Checkbox(selected, onCheckedChange = null)
                Text(text = text)
            }
        },
        emptyContent = { Text(text = "Не найдено") },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}
