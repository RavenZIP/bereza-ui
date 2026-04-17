package com.github.ravenzip.berezaUI.core.components.textfield.dropdown

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
import com.github.ravenzip.berezaUI.core.components.textfield.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.core.components.textfield.SingleLineTextField
import com.github.ravenzip.berezaUI.core.data.*

// TODO ограничить максимальное число видимых элементов в выпадающем списке (желательно через
// параметр)
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun <T> DropDownTextFieldBox(
    sourceState: SourceState<T>,
    onSelectItem: (T) -> Unit,
    keySelector: ((T) -> Any)? = null,
    expanded: Boolean,
    onExpandedChange: (DropDownExpandEvent) -> Unit,
    collapseAfterSelect: Boolean = true,
    modifier: Modifier = Modifier,
    textField: @Composable (Modifier) -> Unit,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit = emptyContent,
    enabled: Boolean = true,
    readOnly: Boolean = false,
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
                        // TODO попробовать перейти на LazyColumn
                        sourceState.items.forEach { item ->
                            val key = if (keySelector != null) keySelector(item) else item

                            key(key) {
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

                else -> {
                    // do nothing
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun <T> DropdownTextField(
    sourceState: SourceState<T>,
    onSelectItem: (T) -> Unit,
    keySelector: ((T) -> Any)? = null,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onExpandedChange: (DropDownExpandEvent) -> Unit = {},
    collapseAfterSelect: Boolean = false,
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
    var expanded by remember { mutableStateOf(false) }

    DropDownTextFieldBox(
        sourceState = sourceState,
        onSelectItem = onSelectItem,
        keySelector = keySelector,
        expanded = expanded,
        onExpandedChange = { expandEvent ->
            expanded = expandEvent is DropDownExpandEvent.Expanded
            onExpandedChange(expandEvent)
        },
        collapseAfterSelect = collapseAfterSelect,
        modifier = modifier,
        textField = { menuAnchor ->
            SingleLineTextField(
                value = text,
                onValueChange = { x -> onTextChange(x) },
                modifier = Modifier.then(menuAnchor),
                isEnabled = enabled,
                isReadonly = readOnly,
                errorState = errorState,
                label = textFieldLabel,
                leadingIcon = textFieldLeadingIcon,
                trailingIcon = textFieldTrailingIcon,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = dropDownMenuItemContent,
        emptyContent = dropDownMenuEmptyContent,
        loadingContent = dropDownMenuLoadingContent,
        enabled = enabled,
        readOnly = readOnly,
        shape = shape,
        colors = colors.menuColors,
    )
}

@Composable
@ExperimentalMaterial3Api
fun <T> OutlinedDropdownTextField(
    sourceState: SourceState<T>,
    onSelectItem: (T) -> Unit,
    keySelector: ((T) -> Any)? = null,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onExpandedChange: (DropDownExpandEvent) -> Unit = {},
    collapseAfterSelect: Boolean = false,
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
    var expanded by remember { mutableStateOf(false) }

    DropDownTextFieldBox(
        sourceState = sourceState,
        onSelectItem = onSelectItem,
        keySelector = keySelector,
        expanded = expanded,
        onExpandedChange = { expandEvent ->
            expanded = expandEvent is DropDownExpandEvent.Expanded
            onExpandedChange(expandEvent)
        },
        collapseAfterSelect = collapseAfterSelect,
        modifier = modifier,
        textField = { menuAnchor ->
            OutlinedSingleLineTextField(
                value = text,
                onValueChange = { x -> onTextChange(x) },
                modifier = Modifier.then(menuAnchor),
                isEnabled = enabled,
                isReadonly = readOnly,
                errorState = errorState,
                label = textFieldLabel,
                leadingIcon = textFieldLeadingIcon,
                trailingIcon = textFieldTrailingIcon,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = dropDownMenuItemContent,
        emptyContent = dropDownMenuEmptyContent,
        loadingContent = dropDownMenuLoadingContent,
        enabled = enabled,
        readOnly = readOnly,
        shape = shape,
        colors = colors.menuColors,
    )
}
