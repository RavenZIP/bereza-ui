package com.github.ravenzip.berezaUI.core.components.textfield.dropdown

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.data.*
import com.github.ravenzip.berezaUI.core.utils.collectAsSnapshotStateList
import com.github.ravenzip.berezaUI.core.utils.collectAsStateLifecycleAware
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

// TODO уйти от дублирования вычисления переменных в компонентах с контролами (глобально, касается
// не только Autocomplete)
@Composable
fun <T> AutocompleteTextField(
    loadedState: LoadedState<T>,
    selected: T,
    onSelectItem: (T) -> Unit,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var text by remember { mutableStateOf("") }

    LaunchedEffect(selected) {
        val selectedText = itemToString(selected)
        text = selectedText.ifBlank { text }
    }

    DropdownTextField(
        loadedState = loadedState,
        onSelectItem = { newSelectedItem ->
            text = itemToString(newSelectedItem)
            onSelectItem(newSelectedItem)
        },
        keySelector = keySelector,
        text = text,
        onTextChange = { newText ->
            text = newText
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = errorState,
        enabled = enabled,
        readOnly = readOnly,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedAutocompleteTextField(
    loadedState: LoadedState<T>,
    selected: T,
    onSelectItem: (T) -> Unit,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = OutlinedDropDownTextFieldDefaults.colors(),
) {
    var text by remember { mutableStateOf("") }

    LaunchedEffect(selected) {
        val selectedText = itemToString(selected)
        text = selectedText.ifBlank { text }
    }

    OutlinedDropdownTextField(
        loadedState = loadedState,
        onSelectItem = { newSelectedItem ->
            text = itemToString(newSelectedItem)
            onSelectItem(newSelectedItem)
        },
        keySelector = keySelector,
        text = text,
        onTextChange = { newText ->
            text = newText
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = errorState,
        enabled = enabled,
        readOnly = readOnly,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> AutocompleteTextField(
    control: MutableFormControl<T>,
    loadedState: LoadedState<T>,
    clearValue: T,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    val value by control.valueChanges.collectAsStateLifecycleAware()
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

    AutocompleteTextField(
        loadedState = loadedState,
        selected = value,
        onSelectItem = { newSelectedItem ->
            control.setValue(newSelectedItem)
            control.markAsDirty()
        },
        itemToString = itemToString,
        keySelector = keySelector,
        onTextChange = { newText ->
            control.setValue(clearValue)
            control.markAsDirty()
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = errorState,
        enabled = status.isEnabled(),
        readOnly = readOnly,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedAutocompleteTextField(
    control: MutableFormControl<T>,
    loadedState: LoadedState<T>,
    clearValue: T,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = OutlinedDropDownTextFieldDefaults.colors(),
) {
    val value by control.valueChanges.collectAsStateLifecycleAware()
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

    OutlinedAutocompleteTextField(
        loadedState = loadedState,
        selected = value,
        onSelectItem = { newSelectedItem ->
            control.setValue(newSelectedItem)
            control.markAsDirty()
        },
        itemToString = itemToString,
        keySelector = keySelector,
        onTextChange = { newText ->
            control.setValue(clearValue)
            control.markAsDirty()
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = errorState,
        enabled = status.isEnabled(),
        readOnly = readOnly,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        shape = shape,
        colors = colors,
    )
}
