package com.github.ravenzip.bereza.core.components.textfield.autocomplete

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.*
import com.github.ravenzip.bereza.core.data.*
import com.github.ravenzip.bereza.core.data.DropDownExpandEvent.Companion.isExpanded
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.flow.Flow

/**
 * [MultiAutocomplete] — компонент с возможностью выбора элементов из списка, который будет получен
 * при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiAutocomplete(
    selected: List<T>,
    displayWith: (T) -> String,
    onSelectionChange: (T, SelectionChange) -> Unit,
    search: (String) -> Flow<List<T>>,
    modifier: Modifier = Modifier,
    searchDebounce: Duration = 500.milliseconds,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        DropDownMenuDefaults.SelectableMenuItem(
            item,
            selected,
            displayWith,
            key,
        )
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

    Search(
        inputText = inputText,
        expanded = expanded,
        search = search,
        searchDebounce = searchDebounce,
        onSourceStateChange = { newSourceState -> sourceState = newSourceState },
    )

    DropDownTextFieldBox(
        sourceState = sourceState,
        onSelectItem = { item ->
            // TODO не дублировать код
            val itemKey = key(item)
            val isSelected = selected.any { iterableItem -> key(iterableItem) == itemKey }

            onSelectionChange(
                item,
                if (isSelected) SelectionChange.Deselect else SelectionChange.Select,
            )
            inputText = ""
        },
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        collapseAfterSelect = false,
        textField = {
            ChipTextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                chips = selected,
                displayWith = displayWith,
                onRemoveChip = { item ->
                    onSelectionChange(
                        item,
                        SelectionChange.Deselect,
                    )
                },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                chipOverflow = chipOverflow,
                errorState = errorState,
                singleLine = true,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
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
 * [OutlinedMultiAutocomplete] — компонент с возможностью выбора элементов из списка, который будет
 * получен при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedMultiAutocomplete(
    selected: List<T>,
    displayWith: (T) -> String,
    onSelectionChange: (T, SelectionChange) -> Unit,
    search: (String) -> Flow<List<T>>,
    modifier: Modifier = Modifier,
    searchDebounce: Duration = 500.milliseconds,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        DropDownMenuDefaults.SelectableMenuItem(
            item,
            selected,
            displayWith,
            key,
        )
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

    Search(
        inputText = inputText,
        expanded = expanded,
        search = search,
        searchDebounce = searchDebounce,
        onSourceStateChange = { newSourceState -> sourceState = newSourceState },
    )

    DropDownTextFieldBox(
        sourceState = sourceState,
        onSelectItem = { item ->
            val itemKey = key(item)
            val isSelected = selected.any { iterableItem -> key(iterableItem) == itemKey }

            onSelectionChange(
                item,
                if (isSelected) SelectionChange.Deselect else SelectionChange.Select,
            )
            inputText = ""
        },
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        collapseAfterSelect = false,
        textField = {
            OutlinedChipTextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                chips = selected,
                displayWith = displayWith,
                onRemoveChip = { item ->
                    onSelectionChange(
                        item,
                        SelectionChange.Deselect,
                    )
                },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                chipOverflow = chipOverflow,
                errorState = errorState,
                singleLine = true,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
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
 * [MultiAutocomplete] — компонент с возможностью выбора элементов из заданного списка, который
 * будет отфильтрован при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiAutocomplete(
    source: List<T>,
    selected: List<T>,
    displayWith: (T) -> String,
    onSelectionChange: (T, SelectionChange) -> Unit,
    search: (T, String) -> Boolean,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        DropDownMenuDefaults.SelectableMenuItem(
            item,
            selected,
            displayWith,
            key,
        )
    },
    emptyContent: @Composable (() -> Unit),
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    val filteredSource = rememberFilteredSource(source, inputText, search)

    DropDownTextFieldBox(
        sourceState = SourceState.Content(filteredSource),
        onSelectItem = { item ->
            val itemKey = key(item)
            val isSelected = selected.any { iterableItem -> key(iterableItem) == itemKey }

            onSelectionChange(
                item,
                if (isSelected) SelectionChange.Deselect else SelectionChange.Select,
            )
            inputText = ""
        },
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        collapseAfterSelect = false,
        textField = {
            ChipTextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                chips = selected,
                displayWith = displayWith,
                onRemoveChip = { item ->
                    onSelectionChange(
                        item,
                        SelectionChange.Deselect,
                    )
                },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                chipOverflow = chipOverflow,
                errorState = errorState,
                singleLine = true,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
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
 * [OutlinedMultiAutocomplete] — компонент с возможностью выбора элементов из заданного списка,
 * который будет отфильтрован при помощи функции [search] мере ввода текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedMultiAutocomplete(
    source: List<T>,
    selected: List<T>,
    displayWith: (T) -> String,
    onSelectionChange: (T, SelectionChange) -> Unit,
    search: (T, String) -> Boolean,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit = { item ->
        DropDownMenuDefaults.SelectableMenuItem(
            item,
            selected,
            displayWith,
            key,
        )
    },
    placeholder: @Composable (() -> Unit)? = null,
    emptyContent: @Composable (() -> Unit),
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.outlinedColors(),
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    val filteredSource = rememberFilteredSource(source, inputText, search)

    DropDownTextFieldBox(
        sourceState = SourceState.Content(filteredSource),
        onSelectItem = { item ->
            val itemKey = key(item)
            val isSelected = selected.any { iterableItem -> key(iterableItem) == itemKey }

            onSelectionChange(
                item,
                if (isSelected) SelectionChange.Deselect else SelectionChange.Select,
            )
            inputText = ""
        },
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        collapseAfterSelect = false,
        textField = {
            OutlinedChipTextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                chips = selected,
                displayWith = displayWith,
                onRemoveChip = { item ->
                    onSelectionChange(
                        item,
                        SelectionChange.Deselect,
                    )
                },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                chipOverflow = chipOverflow,
                errorState = errorState,
                singleLine = true,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
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
