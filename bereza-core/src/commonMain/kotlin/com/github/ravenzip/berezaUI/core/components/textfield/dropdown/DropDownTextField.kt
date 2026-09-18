package com.github.ravenzip.berezaUI.core.components.textfield.dropdown

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults.FocusedBorderThickness
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.TextFieldWithSupportingRow
import com.github.ravenzip.berezaUI.core.data.*

// TODO как-то ограничить количество видимых элементов в выпадающем списке
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun <T> DropDownTextFieldBox(
    sourceState: SourceState<T>,
    onSelectItem: (T) -> Unit,
    expanded: Boolean,
    onExpandedChange: (DropDownExpandEvent) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    collapseAfterSelect: Boolean = true,
    textField: @Composable ExposedDropdownMenuBoxScope.() -> Unit,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit = emptyContent,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownMenuColors = DropDownMenuDefaults.colors(),
) {
    val menuBorder =
        if (colors.borderColor != null) BorderStroke(FocusedBorderThickness, colors.borderColor)
        else null

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded ->
            onExpandedChange(createDropDownExpandEvent(expanded = expanded))
        },
        modifier = modifier,
    ) {
        textField()

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(createDropDownExpandEvent(expanded = false)) },
            border = menuBorder,
            shape = shape,
            containerColor = colors.containerColor,
        ) {
            when (sourceState) {
                is SourceState.Loading -> {
                    DisabledDropDownMenuItem(text = loadingContent)
                }

                is SourceState.Content -> {
                    if (sourceState.items.isEmpty()) {
                        DisabledDropDownMenuItem(text = emptyContent)
                    } else {
                        sourceState.items.forEach { item ->
                            val computedKey = key(item)
                            key(computedKey) {
                                DropdownMenuItem(
                                    text = { itemContent(item) },
                                    onClick = {
                                        onSelectItem(item)

                                        if (collapseAfterSelect) {
                                            onExpandedChange(
                                                createDropDownExpandEvent(
                                                    expanded = false,
                                                    afterSelect = true,
                                                )
                                            )
                                        }
                                    },
                                    enabled = enabled,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 1) Select — компонент с возможностью выбора элемента из заданного списка. Не поддерживает ввод
 *    текста, фильтрацию списка или добавление собственных значений.
 *
 * 2) ComboBox — компонент с возможностью выбора элемента из заданного списка или ввода собственного
 *    значения.
 *
 * 3) Autocomplete — компонент с возможностью выбора элемента из списка, который фильтруется или
 *    формируется по мере ввода текста. Ввод значения, отсутствующего в списке, не поддерживается.
 *
 * Это основная концепция. Можно для этих компонентов сделать multi версию. Должен ли Combobox после
 * ввода собственного значения добавлять его в исходный список? Если да, то это отдельный компонент
 * или просто параметр (условно allowCustomValue) + callback. Коллбэк можно сделать нуллабле и тогда
 * от этого будет зависеть поведение
 */
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
        onExpandedChange = { x -> expanded = x is DropDownExpandEvent.Expanded },
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
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (onClear != null && selected != null) {
                            IconButton(
                                onClick = onClear,
                                enabled = enabled,
                                shape = RoundedCornerShape(14.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Clear,
                                    contentDescription = null,
                                )
                            }
                        }

                        val arrowRotation by
                            animateFloatAsState(targetValue = if (expanded) 180f else 0f)

                        // TODO надо ли оформить это в виде кнопки?
                        Icon(
                            imageVector = Icons.Outlined.ArrowDropDown,
                            contentDescription = null,
                            modifier =
                                Modifier.size(48.dp).padding(end = 12.dp).rotate(arrowRotation),
                        )
                    }
                },
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

// TODO как отображать выбранные?
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiSelect(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: List<T>,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    expanded: Boolean,
    onExpandedChange: (DropDownExpandEvent) -> Unit,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    DropDownTextFieldBox(
        sourceState = SourceState.Content(source),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier,
        key = key,
        textField = {
            TextFieldWithSupportingRow(
                value = "TODO",
                onValueChange = {},
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = enabled,
                    ),
                readonly = true,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                label = label,
                placeholder = placeholder,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val itemKey = key(item)
            val selected = selected.any { key(it) == itemKey }
            val text = remember(item) { displayWith(item) }

            Row {
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

// Как реализовать поиск? Я думал через параметр функции в компоненте, выделив ему state-класс. Либо
// снаружи, но не знаю точно ли надо, ведь это уже самостоятельный компонент
// Как задам архитектуру здесь, так скорее всего она пойдет в Autocomplete потом, как допишу
// Combobox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Combobox(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    expanded: Boolean,
    onExpandedChange: (DropDownExpandEvent) -> Unit,
    onAddItem: ((T) -> Unit)? = null,
    searchWith: (T) -> String,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    val text =
        remember(displayWith, selected) { if (selected != null) displayWith(selected) else "" }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(source),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
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
                readonly = true,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val text = remember(item) { displayWith(item) }
            Text(text = text)
        },
        emptyContent = {
            if (onAddItem != null) {
                // TODO
            } else {
                Text(text = "Не найдено")
            }
        },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

// TODO
@Composable fun MultiCombobox() {}

// TODO
@Composable fun Autocomplete() {}

// TODO
@Composable fun MultiAutocomplete() {}
