package com.github.ravenzip.bereza.core.components.textfield.select

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.DropDownTextFieldBox
import com.github.ravenzip.bereza.core.components.textfield.DropDownTextFieldTrailingContent
import com.github.ravenzip.bereza.core.components.textfield.OutlinedTextFieldWithSupportingRow
import com.github.ravenzip.bereza.core.components.textfield.TextFieldWithSupportingRow
import com.github.ravenzip.bereza.core.data.ComponentErrorState
import com.github.ravenzip.bereza.core.data.DropDownExpandEvent.Companion.isExpanded
import com.github.ravenzip.bereza.core.data.DropDownTextFieldColors
import com.github.ravenzip.bereza.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.bereza.core.data.SourceState

/**
 * [Select] — компонент с возможностью выбора элемента из заданного списка. Не поддерживает ввод
 * текста и фильтрацию списка.
 */
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
    key: (T) -> Any? = { it },
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
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
                maxLines = 1,
                singleLine = true,
                label = label,
                placeholder = placeholder,
                trailingIcon = {
                    DropDownTextFieldTrailingContent(
                        selected = selected,
                        expanded = expanded,
                        enabled = enabled,
                        onClear = onClear,
                        clearIcon = clearIcon,
                        dropDownIcon = dropDownIcon,
                    )
                },
                interactionSource = interactionSource,
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

/**
 * [OutlinedSelect] — компонент с возможностью выбора элемента из заданного списка. Не поддерживает
 * ввод текста и фильтрацию списка.
 */
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
    key: (T) -> Any? = { it },
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
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
                maxLines = 1,
                singleLine = true,
                label = label,
                placeholder = placeholder,
                trailingIcon = {
                    DropDownTextFieldTrailingContent(
                        selected = selected,
                        expanded = expanded,
                        enabled = enabled,
                        onClear = onClear,
                        clearIcon = clearIcon,
                        dropDownIcon = dropDownIcon,
                    )
                },
                interactionSource = interactionSource,
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
