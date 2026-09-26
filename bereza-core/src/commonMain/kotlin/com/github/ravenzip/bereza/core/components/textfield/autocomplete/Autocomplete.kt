package com.github.ravenzip.bereza.core.components.textfield.autocomplete

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.*
import com.github.ravenzip.bereza.core.data.ComponentErrorState
import com.github.ravenzip.bereza.core.data.DropDownExpandEvent.Companion.isExpanded
import com.github.ravenzip.bereza.core.data.DropDownTextFieldColors
import com.github.ravenzip.bereza.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.bereza.core.data.SourceState
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * [Autocomplete] — компонент с возможностью выбора элемента из списка, который будет получен при
 * помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Autocomplete(
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (String) -> Flow<List<T>>,
    searchDebounce: Duration = 500.milliseconds,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        AutocompleteMenuItem(item, displayWith)
    },
    emptyContent: @Composable (() -> Unit),
    loadingContent: @Composable (() -> Unit) = emptyContent,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    var sourceState by remember { mutableStateOf<SourceState<T>>(SourceState.Content(listOf())) }

    ComputeInputText(
        selected = selected,
        displayWith = displayWith,
        onInputTextChange = { newText -> inputText = newText },
    )

    Search(
        inputText = inputText,
        expanded = expanded,
        search = search,
        searchDebounce = searchDebounce,
        onSourceStateChange = { newSourceState -> sourceState = newSourceState },
    )

    DropDownTextFieldBox(
        sourceState = sourceState,
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
        itemContent = itemContent,
        emptyContent = emptyContent,
        loadingContent = loadingContent,
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

/**
 * [OutlinedAutocomplete] — компонент с возможностью выбора элемента из списка, который будет
 * получен при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedAutocomplete(
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (String) -> Flow<List<T>>,
    searchDebounce: Duration = 500.milliseconds,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        AutocompleteMenuItem(item, displayWith)
    },
    emptyContent: @Composable (() -> Unit),
    loadingContent: @Composable (() -> Unit) = emptyContent,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.outlinedColors(),
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    var sourceState by remember { mutableStateOf<SourceState<T>>(SourceState.Content(listOf())) }

    ComputeInputText(
        selected = selected,
        displayWith = displayWith,
        onInputTextChange = { newText -> inputText = newText },
    )

    Search(
        inputText = inputText,
        expanded = expanded,
        search = search,
        searchDebounce = searchDebounce,
        onSourceStateChange = { newSourceState -> sourceState = newSourceState },
    )

    DropDownTextFieldBox(
        sourceState = sourceState,
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        textField = {
            OutlinedTextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
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
        itemContent = itemContent,
        emptyContent = emptyContent,
        loadingContent = loadingContent,
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

/**
 * [Autocomplete] — компонент с возможностью выбора элемента из заданного списка, который будет
 * отфильтрован при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Autocomplete(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (T, String) -> Boolean,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        AutocompleteMenuItem(item, displayWith)
    },
    emptyContent: @Composable (() -> Unit),
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    val filteredSource = rememberFilteredSource(source, inputText, search)

    ComputeInputText(
        selected = selected,
        displayWith = displayWith,
        onInputTextChange = { newText -> inputText = newText },
    )

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
        itemContent = itemContent,
        emptyContent = emptyContent,
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

/**
 * [OutlinedAutocomplete] — компонент с возможностью выбора элемента из заданного списка, который
 * будет отфильтрован при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedAutocomplete(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (T, String) -> Boolean,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        AutocompleteMenuItem(item, displayWith)
    },
    emptyContent: @Composable (() -> Unit),
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.outlinedColors(),
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    val filteredSource = rememberFilteredSource(source, inputText, search)

    ComputeInputText(
        selected = selected,
        displayWith = displayWith,
        onInputTextChange = { newText -> inputText = newText },
    )

    DropDownTextFieldBox(
        sourceState = SourceState.Content(filteredSource),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        textField = {
            OutlinedTextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
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
        itemContent = itemContent,
        emptyContent = emptyContent,
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

@Composable
private fun <T> AutocompleteMenuItem(
    item: T,
    displayWith: (T) -> String,
) {
    val text = remember(item) { displayWith(item) }
    Text(text)
}
