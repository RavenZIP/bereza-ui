package com.github.ravenzip.berezaUI.reactive.components.textfield.dropdown

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.AutocompleteTextField
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.OutlinedAutocompleteTextField
import com.github.ravenzip.berezaUI.core.data.*
import com.github.ravenzip.berezaUI.core.utils.collectAsSnapshotStateList
import com.github.ravenzip.berezaUI.core.utils.collectAsStateLifecycleAware
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

// TODO уйти от дублирования вычисления переменных в компонентах с контролами (глобально, касается
// не только Autocomplete)
@Composable
@ExperimentalMaterial3Api
fun <T> AutocompleteTextField(
    control: MutableFormControl<T>,
    sourceState: SourceState<T>,
    clearValue: T,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onExpandedChange: (DropDownExpandEvent) -> Unit = {},
    collapseAfterSelect: Boolean = true,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuEmptyContent: @Composable () -> Unit,
    dropDownMenuLoadingContent: @Composable () -> Unit = dropDownMenuEmptyContent,
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
        sourceState = sourceState,
        selected = value,
        onSelectItem = { newSelected ->
            control.setValue(newSelected)
            control.markAsDirty()
        },
        onClearSelected = { control.setValue(clearValue) },
        itemToString = itemToString,
        keySelector = keySelector,
        onTextChange = { newText ->
            control.markAsDirty()
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = errorState,
        enabled = status.isEnabled(),
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        collapseAfterSelect = collapseAfterSelect,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuEmptyContent = dropDownMenuEmptyContent,
        dropDownMenuLoadingContent = dropDownMenuLoadingContent,
        shape = shape,
        colors = colors,
    )
}

@Composable
@ExperimentalMaterial3Api
fun <T> OutlinedAutocompleteTextField(
    control: MutableFormControl<T>,
    sourceState: SourceState<T>,
    clearValue: T,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onExpandedChange: (DropDownExpandEvent) -> Unit = {},
    collapseAfterSelect: Boolean = true,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuEmptyContent: @Composable () -> Unit,
    dropDownMenuLoadingContent: @Composable () -> Unit = dropDownMenuEmptyContent,
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
        sourceState = sourceState,
        selected = value,
        onSelectItem = { newSelectedItem ->
            control.setValue(newSelectedItem)
            control.markAsDirty()
        },
        onClearSelected = { control.setValue(clearValue) },
        itemToString = itemToString,
        keySelector = keySelector,
        onTextChange = { newText ->
            control.markAsDirty()
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = errorState,
        enabled = status.isEnabled(),
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        collapseAfterSelect = collapseAfterSelect,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuEmptyContent = dropDownMenuEmptyContent,
        dropDownMenuLoadingContent = dropDownMenuLoadingContent,
        shape = shape,
        colors = colors,
    )
}
