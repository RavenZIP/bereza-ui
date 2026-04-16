package com.github.ravenzip.berezaUI.core.components.textfield.dropdown

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.data.*

// TODO временно, пока не придумаю как это оформить лучше
private fun textNotEqualAfterManualCollapse(
    firstText: String,
    secondText: String,
    expandedEvent: DropDownExpandEvent,
): Boolean {
    return expandedEvent is DropDownExpandEvent.Collapsed &&
        !expandedEvent.afterSelect &&
        firstText != secondText
}

@Composable
@ExperimentalMaterial3Api
fun <T> AutocompleteTextField(
    sourceState: SourceState<T>,
    selected: T,
    onSelectItem: (T) -> Unit,
    onClearSelected: () -> Unit,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    enabled: Boolean = true,
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
    var text by remember(selected) { mutableStateOf(itemToString(selected)) }

    DropdownTextField(
        sourceState = sourceState,
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
        onExpandedChange = { expandedEvent ->
            /**
             * Если после закрытия выпадающего списка введенное значение не совпадает с выбранным,
             * то вызываем событие очистки значения, т.к. считаем, что оно стало невалидным
             *
             * Реализовать такой функционал при вводе текста трудно, поэтому завязываемся на
             * окончание работы с поиском (закрытие выпадающего списка)
             *
             * Для [OutlinedAutocompleteTextField] аналогично
             */
            if (textNotEqualAfterManualCollapse(itemToString(selected), text, expandedEvent)) {
                onClearSelected()
            }

            onExpandedChange(expandedEvent)
        },
        collapseAfterSelect = collapseAfterSelect,
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
    sourceState: SourceState<T>,
    selected: T,
    onSelectItem: (T) -> Unit,
    onClearSelected: () -> Unit,
    itemToString: (T) -> String,
    keySelector: ((T) -> Any)? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    enabled: Boolean = true,
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
    var text by remember(selected) { mutableStateOf(itemToString(selected)) }

    OutlinedDropdownTextField(
        sourceState = sourceState,
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
        onExpandedChange = { expandedEvent ->
            /** @see [AutocompleteTextField]] */
            if (textNotEqualAfterManualCollapse(itemToString(selected), text, expandedEvent)) {
                onClearSelected()
            }

            onExpandedChange(expandedEvent)
        },
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
