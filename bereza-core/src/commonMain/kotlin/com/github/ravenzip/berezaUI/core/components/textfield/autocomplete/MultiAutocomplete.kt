package com.github.ravenzip.berezaUI.core.components.textfield.autocomplete

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
import com.github.ravenzip.berezaUI.core.components.textfield.*
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.DropDownExpandEvent.Companion.isExpanded
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldColors
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.berezaUI.core.data.SourceState
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiAutocomplete(
    modifier: Modifier = Modifier,
    selected: List<T> = listOf(),
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    onSelect: (T) -> Unit,
    search: (String) -> Flow<List<T>>,
    searchDebounce: Duration = 500.milliseconds,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
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
        onSelectItem = { x ->
            onSelect(x)
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
                onRemoveChip = onRemoveChip,
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                chipOverflow = chipOverflow,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                singleLine = true,
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
        emptyContent = {
            // TODO нужно как-то дать возможность прокинуть свой контент
            // Возможно, что стоит поступить как с TrailingIcon в ExposedDropdownMenuBoxScope,
            // который предоставляет дефолтное поведение и снаружи получать строку
            // Либо снаружи получать emptyContent: @Composable () -> Unit

            Text(text = "Не найдено")
        },
        loadingContent = {
            Text(text = "Загрузка...")
        },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedMultiAutocomplete(
    modifier: Modifier = Modifier,
    selected: List<T> = listOf(),
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    onSelect: (T) -> Unit,
    search: (String) -> Flow<List<T>>,
    searchDebounce: Duration = 500.milliseconds,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
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
        onSelectItem = { x ->
            onSelect(x)
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
                onRemoveChip = onRemoveChip,
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                chipOverflow = chipOverflow,
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                singleLine = true,
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
        emptyContent = {
            // TODO нужно как-то дать возможность прокинуть свой контент
            // Возможно, что стоит поступить как с TrailingIcon в ExposedDropdownMenuBoxScope,
            // который предоставляет дефолтное поведение и снаружи получать строку
            // Либо снаружи получать emptyContent: @Composable () -> Unit

            Text(text = "Не найдено")
        },
        loadingContent = {
            Text(text = "Загрузка...")
        },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}
