package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextFieldDefaults.FocusedBorderThickness
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.SingleLineTextField
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.autocomplete.*
import com.github.ravenzip.berezaUI.core.effects.SearchEffect
import com.github.ravenzip.berezaUI.core.utils.collectAsSnapshotStateList
import com.github.ravenzip.berezaUI.core.utils.collectAsStateLifecycleAware
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

// TODO уйти от дублирования SearchEffect и вычисления переменных в компонентах с контролами
// TODO добавить возможность прокидывать иконки для элемента меню
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutocompleteBox(
    state: AutocompleteState<T>,
    modifier: Modifier = Modifier,
    textField: @Composable (Modifier) -> Unit,
    dropDownMenuItemText: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    onSelectItem: (T) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    colors: AutocompleteMenuColors = AutocompleteMenuDefaults.colors(),
) {
    val expanded by state.expanded.collectAsStateLifecycleAware()
    val result by state.result.collectAsStateLifecycleAware()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { x -> state.setExpanded(x) },
        modifier = modifier,
    ) {
        textField(
            Modifier.menuAnchor(
                type =
                    if (readOnly) ExposedDropdownMenuAnchorType.PrimaryNotEditable
                    else ExposedDropdownMenuAnchorType.PrimaryEditable,
                enabled = enabled,
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { state.setExpanded(false) },
            border =
                if (colors.borderColor != null)
                    BorderStroke(FocusedBorderThickness, colors.borderColor)
                else null,
            shape = RoundedCornerShape(12.dp),
            containerColor = colors.containerColor,
        ) {
            if (result.isEmpty()) {
                DropdownMenuItem(text = dropDownMenuItemPlaceholder, onClick = {}, enabled = false)
            } else {
                result.forEach { item ->
                    DropdownMenuItem(
                        text = { dropDownMenuItemText(item) },
                        onClick = {
                            onSelectItem(item)
                            state.setExpanded(false)
                        },
                        enabled = enabled,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutocompleteTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    state: AutocompleteState<T>,
    onSelectItem: (T) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: AutocompleteTextFieldColors = AutocompleteTextFieldDefaults.colors(),
) {
    SearchEffect(state = state, searchQuery = { text })

    AutocompleteBox(
        state = state,
        textField = { menuAnchor ->
            SingleLineTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = modifier.then(menuAnchor),
                isEnabled = enabled,
                isReadonly = readOnly,
                errorState = errorState,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        onSelectItem = onSelectItem,
        enabled = enabled,
        readOnly = readOnly,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        colors = colors.menuColors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedAutocompleteTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    state: AutocompleteState<T>,
    onSelectItem: (T) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: AutocompleteTextFieldColors = OutlinedAutocompleteTextFieldDefaults.colors(),
) {
    SearchEffect(state = state, searchQuery = { text })

    AutocompleteBox(
        state = state,
        textField = { menuAnchor ->
            OutlinedSingleLineTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = modifier.then(menuAnchor),
                isEnabled = enabled,
                isReadonly = readOnly,
                errorState = errorState,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        onSelectItem = onSelectItem,
        enabled = enabled,
        readOnly = readOnly,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        colors = colors.menuColors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutocompleteTextField(
    modifier: Modifier = Modifier,
    control: MutableFormControl<T>,
    clearValue: T,
    source: AutocompleteSource<T>,
    sourceItemToString: (T) -> String,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: AutocompleteTextFieldColors = AutocompleteTextFieldDefaults.colors(),
) {
    val state = remember(control, source) { AutocompleteState(source) }
    var text by remember { mutableStateOf("") }

    val status by control.statusChanges.collectAsStateLifecycleAware()
    val dirty by control.dirtyChanges.collectAsStateLifecycleAware()
    val touched by control.touchedChanges.collectAsStateLifecycleAware()
    val errorMessage =
        control.errorsChanges.collectAsSnapshotStateList().firstOrNull()?.message ?: ""

    val errorState =
        remember(status, dirty, touched) {
            if (status.isInvalid() && (dirty || touched)) ComponentErrorState.Error(errorMessage)
            else ComponentErrorState.Ok
        }

    SearchEffect(state = state, searchQuery = { text })

    AutocompleteBox(
        state = state,
        textField = { menuAnchor ->
            SingleLineTextField(
                value = text,
                onValueChange = { x ->
                    text = x
                    onTextChange(x)
                    control.markAsDirty()

                    if (x.isEmpty()) {
                        control.setValue(clearValue)
                    }
                },
                modifier = modifier.then(menuAnchor),
                isEnabled = status.isEnabled(),
                isReadonly = readOnly,
                errorState = errorState,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        onSelectItem = { x ->
            text = sourceItemToString(x)
            control.setValue(x)
            control.markAsDirty()
        },
        enabled = status.isEnabled(),
        readOnly = readOnly,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        colors = colors.menuColors,
    )
}

// TODO чистить контрол, если в поле введен текст, который не матчится со значениями в списке
// TODO чистить текстовое поле после потери фокуса, если значение невалидное
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedAutocompleteTextField(
    modifier: Modifier = Modifier,
    control: MutableFormControl<T>,
    clearValue: T,
    source: AutocompleteSource<T>,
    sourceItemToString: (T) -> String,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: AutocompleteTextFieldColors = OutlinedAutocompleteTextFieldDefaults.colors(),
) {
    val state = remember(control, source) { AutocompleteState(source) }
    var text by remember { mutableStateOf("") }

    val status by control.statusChanges.collectAsStateLifecycleAware()
    val dirty by control.dirtyChanges.collectAsStateLifecycleAware()
    val touched by control.touchedChanges.collectAsStateLifecycleAware()
    val errorMessage =
        control.errorsChanges.collectAsSnapshotStateList().firstOrNull()?.message ?: ""

    val errorState =
        remember(status, dirty, touched) {
            if (status.isInvalid() && (dirty || touched)) ComponentErrorState.Error(errorMessage)
            else ComponentErrorState.Ok
        }

    SearchEffect(state = state, searchQuery = { text })

    AutocompleteBox(
        state = state,
        textField = { menuAnchor ->
            OutlinedSingleLineTextField(
                value = text,
                onValueChange = { x ->
                    text = x
                    onTextChange(x)
                    control.markAsDirty()

                    if (x.isEmpty()) {
                        control.setValue(clearValue)
                    }
                },
                modifier = modifier.then(menuAnchor),
                isEnabled = status.isEnabled(),
                isReadonly = readOnly,
                errorState = errorState,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        onSelectItem = { x ->
            text = sourceItemToString(x)
            control.setValue(x)
            control.markAsDirty()
        },
        enabled = status.isEnabled(),
        readOnly = readOnly,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        colors = colors.menuColors,
    )
}
