package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults.FocusedBorderThickness
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.SingleLineTextField
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldSource
import com.github.ravenzip.berezaUI.core.effects.ExpandedChangeEffect
import com.github.ravenzip.berezaUI.core.effects.LoadSearchResult
import com.github.ravenzip.berezaUI.core.utils.collectAsSnapshotStateList
import com.github.ravenzip.berezaUI.core.utils.collectAsStateLifecycleAware
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

// TODO добавить возможность прокидывать иконки для элемента меню
// TODO добавить возможность отключить границы у меню
// TODO добавить возможность установки своих модификаторов в меню
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun <T> BasicDropDownTextField(
    source: DropDownTextFieldSource<T>,
    sourceItemToString: (T) -> String,
    text: String,
    textField: @Composable (menuAnchor: Modifier) -> Unit,
    onSelectItem: (T) -> Unit,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onExpandedChange: (Boolean) -> Unit = {},
    dropDownMenuItemText: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    menuBorderColor: Color,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var results by remember { mutableStateOf<List<T>>(emptyList()) }

    LoadSearchResult(
        source = source,
        expanded = { expanded },
        searchQuery = { text },
        sourceItemToString = sourceItemToString,
        onSearchStarted = { results = emptyList() },
        onSearchFinished = { x -> results = x },
    )

    ExpandedChangeEffect(expanded = { expanded }, onExpandedChange = onExpandedChange)

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { x -> expanded = x }) {
        textField(
            Modifier.menuAnchor(
                if (readOnly) ExposedDropdownMenuAnchorType.PrimaryNotEditable
                else ExposedDropdownMenuAnchorType.PrimaryEditable,
                enabled,
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier.border(
                    width = FocusedBorderThickness,
                    color = menuBorderColor,
                    shape = RoundedCornerShape(12.dp),
                ),
            shape = RoundedCornerShape(12.dp),
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            if (results.isNotEmpty()) {
                results.forEach { item ->
                    DropdownMenuItem(
                        text = { dropDownMenuItemText(item) },
                        onClick = {
                            onSelectItem(item)
                            expanded = false
                        },
                        enabled = enabled,
                    )
                }
            } else {
                DropdownMenuItem(text = dropDownMenuItemPlaceholder, onClick = {}, enabled = false)
            }
        }
    }
}

// TODO убрать выделение меню, должно быть только у Outlined версии
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    source: DropDownTextFieldSource<T>,
    sourceItemToString: (T) -> String,
    onSelectItem: (T) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    onExpandedChange: (Boolean) -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    BasicDropDownTextField(
        source = source,
        sourceItemToString = sourceItemToString,
        text = text,
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
                colors = colors,
            )
        },
        onSelectItem = onSelectItem,
        enabled = enabled,
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        menuBorderColor = colors.focusedLabelColor,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedDropDownTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    source: DropDownTextFieldSource<T>,
    sourceItemToString: (T) -> String,
    onSelectItem: (T) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    onExpandedChange: (Boolean) -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    BasicDropDownTextField(
        source = source,
        sourceItemToString = sourceItemToString,
        text = text,
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
                colors = colors,
            )
        },
        onSelectItem = onSelectItem,
        enabled = enabled,
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        menuBorderColor = colors.focusedLabelColor,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownTextField(
    modifier: Modifier = Modifier,
    control: MutableFormControl<T>,
    clearValue: T,
    source: DropDownTextFieldSource<T>,
    sourceItemToString: (T) -> String,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    onExpandedChange: (Boolean) -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    var text by remember { mutableStateOf("") }

    val status = control.statusChanges.collectAsStateLifecycleAware().value
    val dirty = control.dirtyChanges.collectAsStateLifecycleAware().value
    val touched = control.touchedChanges.collectAsStateLifecycleAware().value
    val errorMessage =
        control.errorsChanges.collectAsSnapshotStateList().firstOrNull()?.message ?: ""

    val errorState =
        remember(status, dirty, touched) {
            if (status.isInvalid() && (dirty || touched)) ComponentErrorState.Error(errorMessage)
            else ComponentErrorState.Ok
        }

    BasicDropDownTextField(
        source = source,
        sourceItemToString = sourceItemToString,
        text = text,
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
                colors = colors,
            )
        },
        onSelectItem = { x ->
            text = sourceItemToString(x)
            control.setValue(x)
            control.markAsDirty()
        },
        enabled = status.isEnabled(),
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        menuBorderColor = colors.focusedLabelColor,
    )
}

// TODO чистить контрол, если в поле введен текст, который не матчится со значениями в списке
// TODO чистить текстовое поле после потери фокуса, если значение невалидное
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OutlinedDropDownTextField(
    modifier: Modifier = Modifier,
    control: MutableFormControl<T>,
    clearValue: T,
    source: DropDownTextFieldSource<T>,
    sourceItemToString: (T) -> String,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    onExpandedChange: (Boolean) -> Unit = {},
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (T) -> Unit,
    dropDownMenuItemPlaceholder: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    var text by remember { mutableStateOf("") }

    val status = control.statusChanges.collectAsStateLifecycleAware().value
    val dirty = control.dirtyChanges.collectAsStateLifecycleAware().value
    val touched = control.touchedChanges.collectAsStateLifecycleAware().value
    val errorMessage =
        control.errorsChanges.collectAsSnapshotStateList().firstOrNull()?.message ?: ""

    val errorState =
        remember(status, dirty, touched) {
            if (status.isInvalid() && (dirty || touched)) ComponentErrorState.Error(errorMessage)
            else ComponentErrorState.Ok
        }

    BasicDropDownTextField(
        source = source,
        sourceItemToString = sourceItemToString,
        text = text,
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
                colors = colors,
            )
        },
        onSelectItem = { x ->
            text = sourceItemToString(x)
            control.setValue(x)
            control.markAsDirty()
        },
        enabled = status.isEnabled(),
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        dropDownMenuItemText = dropDownMenuItemContent,
        dropDownMenuItemPlaceholder = dropDownMenuItemPlaceholder,
        menuBorderColor = colors.focusedLabelColor,
    )
}
