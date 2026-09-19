package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.animation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.FocusLostEffect
import com.github.ravenzip.berezaUI.core.components.text.HintText
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.unwrapErrorMessage

// TODO много дублирующегося кода
// TODO не слишком ли мелковаты крестики у чипов?

@Composable
fun <T> ChipTextField(
    value: String,
    onValueChange: (String) -> Unit,
    chips: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    isError: Boolean = false,
    textFieldLabel: @Composable (() -> Unit)? = null,
    textFieldPlaceholder: @Composable (() -> Unit)? = null,
    textFieldLeadingIcon: @Composable (() -> Unit)? = null,
    textFieldTrailingIcon: @Composable (() -> Unit)? = null,
    textFieldSupportingText: @Composable (() -> Unit)? = null,
    chipLabel: @Composable (T) -> Unit = { x -> Text(displayWith(x)) },
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        modifier =
            modifier.defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight,
            ),
        interactionSource = interactionSource,
        decorationBox = {
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = {
                    // TODO row + scroll или flowRow со увеличением по горизонтали при большом
                    // кол-ве элементов?
                    //                    Row(modifier =
                    // Modifier.horizontalScroll(rememberScrollState())) {
                    //                        chips.forEach { chip ->
                    //                            InputChip(
                    //                                selected = false,
                    //                                onClick = {},
                    //                                label = { chipLabel(chip) },
                    //                                modifier = Modifier.height(24.dp),
                    //                                trailingIcon = {
                    //                                    Icon(
                    //                                        imageVector = Icons.Default.Close,
                    //                                        contentDescription = "Удалить $chip",
                    //                                        modifier =
                    //
                    // Modifier.size(InputChipDefaults.IconSize).padding(2.dp),
                    //                                    )
                    //                                },
                    //                            )
                    //                        }
                    //                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        chips.forEach { chip ->
                            InputChip(
                                selected = false,
                                onClick = {},
                                label = { chipLabel(chip) },
                                modifier = Modifier.height(24.dp),
                                trailingIcon = {
                                    IconButton(
                                        onClick = { onRemoveChip(chip) },
                                        modifier = Modifier.size(16.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Удалить $chip",
                                            modifier =
                                                Modifier.size(InputChipDefaults.IconSize)
                                                    .padding(2.dp),
                                        )
                                    }
                                },
                            )
                        }
                    }
                },
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                label = textFieldLabel,
                placeholder = textFieldPlaceholder,
                leadingIcon = textFieldLeadingIcon,
                trailingIcon = textFieldTrailingIcon,
                supportingText = textFieldSupportingText,
                container = {
                    TextFieldDefaults.Container(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = shape,
                    )
                },
                colors = colors,
            )
        },
    )
}

@Composable
fun <T> OutlinedChipTextField(
    value: String,
    onValueChange: (String) -> Unit,
    chips: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    isError: Boolean = false,
    textFieldLabel: @Composable (() -> Unit)? = null,
    textFieldPlaceholder: @Composable (() -> Unit)? = null,
    textFieldLeadingIcon: @Composable (() -> Unit)? = null,
    textFieldTrailingIcon: @Composable (() -> Unit)? = null,
    textFieldSupportingText: @Composable (() -> Unit)? = null,
    chipLabel: @Composable (T) -> Unit = { x -> Text(displayWith(x)) },
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        modifier =
            modifier.defaultMinSize(
                minWidth = OutlinedTextFieldDefaults.MinWidth,
                minHeight = OutlinedTextFieldDefaults.MinHeight,
            ),
        interactionSource = interactionSource,
        decorationBox = {
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        chips.forEach { chip ->
                            InputChip(
                                selected = false,
                                onClick = {},
                                label = { chipLabel(chip) },
                                modifier = Modifier.height(24.dp),
                                trailingIcon = {
                                    IconButton(
                                        onClick = { onRemoveChip(chip) },
                                        modifier = Modifier.size(16.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Удалить $chip",
                                            modifier =
                                                Modifier.size(InputChipDefaults.IconSize)
                                                    .padding(2.dp),
                                        )
                                    }
                                },
                            )
                        }
                    }
                },
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                label = textFieldLabel,
                placeholder = textFieldPlaceholder,
                leadingIcon = textFieldLeadingIcon,
                trailingIcon = textFieldTrailingIcon,
                supportingText = textFieldSupportingText,
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = shape,
                    )
                },
                colors = colors,
            )
        },
    )
}

@Composable
fun <T> ChipTextFieldWithSupportingRow(
    value: String,
    onValueChange: (String) -> Unit,
    chips: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    singleLine: Boolean = false,
    textFieldLabel: @Composable (() -> Unit)? = null,
    textFieldPlaceholder: @Composable (() -> Unit)? = null,
    textFieldLeadingIcon: @Composable (() -> Unit)? = null,
    textFieldTrailingIcon: @Composable (() -> Unit)? = null,
    chipLabel: @Composable (T) -> Unit = { x -> Text(displayWith(x)) },
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val isFocused = rememberSaveable { mutableStateOf(false) }
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    FocusLostEffect(focusedState = isFocused, onFocusLost = onTouchChange)

    ChipTextField(
        value = value,
        onValueChange = onValueChange,
        chips = chips,
        displayWith = displayWith,
        onRemoveChip = onRemoveChip,
        modifier =
            modifier.onFocusChanged { x ->
                isFocused.value = x.isFocused
                onFocusChange(x)
            },
        enabled = enabled,
        readOnly = readonly,
        singleLine = singleLine,
        isError = isError,
        textFieldLabel = textFieldLabel,
        textFieldPlaceholder = textFieldPlaceholder,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        textFieldSupportingText =
            if (reserveSupportingContentSpace || errorMessage.isNotEmpty()) {
                {
                    AnimatedVisibility(
                        visible = errorMessage.isNotEmpty(),
                        enter = slideInVertically() + fadeIn(),
                        exit = slideOutVertically() + fadeOut(),
                    ) {
                        HintText(text = errorMessage, color = colors.errorLabelColor)
                    }
                }
            } else null,
        chipLabel = chipLabel,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedChipTextFieldWithSupportingRow(
    value: String,
    onValueChange: (String) -> Unit,
    chips: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    singleLine: Boolean = false,
    textFieldLabel: @Composable (() -> Unit)? = null,
    textFieldPlaceholder: @Composable (() -> Unit)? = null,
    textFieldLeadingIcon: @Composable (() -> Unit)? = null,
    textFieldTrailingIcon: @Composable (() -> Unit)? = null,
    chipLabel: @Composable (T) -> Unit = { x -> Text(displayWith(x)) },
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    // TODO нужны ли onFocusChange и onTouchChange в этих компонентах? Пока это просто копипаста из
    // TextFieldWithSupportingRow
    val isFocused = rememberSaveable { mutableStateOf(false) }
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    FocusLostEffect(focusedState = isFocused, onFocusLost = onTouchChange)

    OutlinedChipTextField(
        value = value,
        onValueChange = onValueChange,
        chips = chips,
        displayWith = displayWith,
        onRemoveChip = onRemoveChip,
        modifier =
            modifier.onFocusChanged { x ->
                isFocused.value = x.isFocused
                onFocusChange(x)
            },
        enabled = enabled,
        readOnly = readonly,
        singleLine = singleLine,
        isError = isError,
        textFieldLabel = textFieldLabel,
        textFieldPlaceholder = textFieldPlaceholder,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        textFieldSupportingText =
            if (reserveSupportingContentSpace || errorMessage.isNotEmpty()) {
                {
                    AnimatedVisibility(
                        visible = errorMessage.isNotEmpty(),
                        enter = slideInVertically() + fadeIn(),
                        exit = slideOutVertically() + fadeOut(),
                    ) {
                        HintText(text = errorMessage, color = colors.errorLabelColor)
                    }
                }
            } else null,
        chipLabel = chipLabel,
        shape = shape,
        colors = colors,
    )
}
