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
import com.github.ravenzip.berezaUI.core.data.DropDownExpandEvent.Companion.isExpanded

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Combobox(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (T, String) -> Boolean,
    onAddItem: ((String) -> Unit)? = null,
    onClear: (() -> Unit)? = null,
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
    val selectedItemText =
        remember(displayWith, selected) { if (selected != null) displayWith(selected) else "" }

    var inputText by remember(selectedItemText) { mutableStateOf(selectedItemText) }

    val filteredSource =
        remember(source, inputText, search) {
            source.filter { item ->
                search(item, inputText)
            }
        }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(filteredSource),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        textField = {
            TextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
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
        emptyContent = {
            // TODO нужно как-то дать возможность прокинуть свой контент
            // Возможно, что стоит поступить как с TrailingIcon в ExposedDropdownMenuBoxScope,
            // который предоставляет дефолтное поведение
            // Либо костяк оставить, а снаружи получать text: @Composable () -> Unit

            // TODO надо ли при нажатии добавить автоматически выбирать элемент? Если да,
            // тогда список с элементами, которые отображаются в выпадающем списке, должен храниться
            // на стороне компонента и будет состоять из исходного списка + того, что натыкал юзер
            // Либо же оставить это на откуп пользователю, захочет - реализует, после подстановки
            // в selected компонент сам отреагирует
            if (onAddItem != null) {
                TextButton(onClick = { onAddItem(inputText) }) {
                    Text("Добавить")
                }
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

@Composable
private fun TrailingContent(
    selected: Any?,
    expanded: Boolean,
    enabled: Boolean,
    onClear: (() -> Unit)?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (onClear != null && selected != null) {
            ClearButton(onClear, enabled)
        }

        AnimatedArrow(expanded)
    }
}

@Composable
private fun ClearButton(onClear: () -> Unit, enabled: Boolean) {
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

@Composable
private fun AnimatedArrow(expanded: Boolean) {
    val arrowRotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Icon(
        imageVector = Icons.Outlined.ArrowDropDown,
        contentDescription = null,
        modifier = Modifier.size(48.dp).padding(end = 12.dp).rotate(arrowRotation),
    )
}
