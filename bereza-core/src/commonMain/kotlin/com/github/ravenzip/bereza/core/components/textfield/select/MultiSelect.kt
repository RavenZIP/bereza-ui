package com.github.ravenzip.bereza.core.components.textfield.select

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
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

/**
 * [MultiSelect] — компонент с возможностью выбора нескольких элементов из заданного списка. Не
 * поддерживает ввод текста и фильтрацию списка.
 */
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
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
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
                chipOverflow = chipOverflow,
                readonly = true,
                errorState = errorState,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
                interactionSource = interactionSource,
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

/**
 * [OutlinedMultiSelect] — компонент с возможностью выбора нескольких элементов из заданного списка.
 * Не поддерживает ввод текста и фильтрацию списка.
 */
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
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
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
                chipOverflow = chipOverflow,
                readonly = true,
                errorState = errorState,
                textFieldLabel = label,
                textFieldPlaceholder = placeholder,
                textFieldTrailingIcon = { AnimatedArrow(expanded) },
                interactionSource = interactionSource,
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
