package com.github.ravenzip.bereza.core.components.textfield

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.Chip
import com.github.ravenzip.bereza.core.data.ComponentErrorState
import com.github.ravenzip.bereza.core.data.unwrapErrorMessage

// TODO не слишком ли мелковаты крестики у чипов?
@Composable
fun <T> ChipTextField(
    value: String,
    onValueChange: (String) -> Unit,
    chips: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    modifier: Modifier = Modifier,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
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
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

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
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = {
                    ChipTextFieldContent(
                        chips = chips,
                        chipOverflow = chipOverflow,
                        chipLabel = chipLabel,
                        onRemoveChip = onRemoveChip,
                        enabled = enabled,
                        readOnly = readOnly,
                        innerTextField = innerTextField,
                    )
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
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
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
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

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
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = {
                    ChipTextFieldContent(
                        chips = chips,
                        chipOverflow = chipOverflow,
                        chipLabel = chipLabel,
                        onRemoveChip = onRemoveChip,
                        enabled = enabled,
                        readOnly = readOnly,
                        innerTextField = innerTextField,
                    )
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
private fun <T> ChipTextFieldContent(
    chips: List<T>,
    chipOverflow: ChipOverflow,
    chipLabel: @Composable (T) -> Unit,
    onRemoveChip: (T) -> Unit,
    enabled: Boolean,
    readOnly: Boolean,
    innerTextField: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        when (chipOverflow) {
            ChipOverflow.Wrap -> {
                WrapChips(
                    chips = chips,
                    label = chipLabel,
                    onRemove = onRemoveChip,
                    enabled = enabled,
                )
            }

            ChipOverflow.HorizontalScroll -> {
                HorizontalScrollChips(
                    chips = chips,
                    label = chipLabel,
                    onRemove = onRemoveChip,
                    enabled = enabled,
                )
            }
        }

        if (!readOnly) {
            innerTextField()
        }
    }
}

@Composable
private fun <T> HorizontalScrollChips(
    chips: List<T>,
    label: @Composable ((T) -> Unit),
    onRemove: (T) -> Unit,
    enabled: Boolean,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        chips.forEach { chip ->
            TextFieldChip(
                label = { label(chip) },
                onRemove = { onRemove(chip) },
                enabled = enabled,
            )
        }
    }
}

@Composable
private fun <T> WrapChips(
    chips: List<T>,
    label: @Composable ((T) -> Unit),
    onRemove: (T) -> Unit,
    enabled: Boolean,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        chips.forEach { chip ->
            TextFieldChip(
                label = { label(chip) },
                onRemove = { onRemove(chip) },
                enabled = enabled,
            )
        }
    }
}

@Composable
private fun TextFieldChip(
    label: @Composable (() -> Unit),
    onRemove: () -> Unit,
    enabled: Boolean,
) {
    Chip(
        label = label,
        modifier = Modifier.height(24.dp),
        enabled = enabled,
        trailingIcon = {
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Удалить Chip",
                    modifier = Modifier.size(InputChipDefaults.IconSize).padding(2.dp),
                )
            }
        },
    )
}

enum class ChipOverflow {
    Wrap,
    HorizontalScroll,
}

@Composable
fun <T> ChipTextFieldWithSupportingRow(
    value: String,
    onValueChange: (String) -> Unit,
    chips: List<T>,
    displayWith: (T) -> String,
    onRemoveChip: (T) -> Unit,
    modifier: Modifier = Modifier,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    enabled: Boolean = true,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    singleLine: Boolean = false,
    textFieldLabel: @Composable (() -> Unit)? = null,
    textFieldPlaceholder: @Composable (() -> Unit)? = null,
    textFieldLeadingIcon: @Composable (() -> Unit)? = null,
    textFieldTrailingIcon: @Composable (() -> Unit)? = null,
    chipLabel: @Composable (T) -> Unit = { x -> Text(displayWith(x)) },
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    ChipTextField(
        value = value,
        onValueChange = onValueChange,
        chips = chips,
        displayWith = displayWith,
        onRemoveChip = onRemoveChip,
        modifier = modifier,
        chipOverflow = chipOverflow,
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
                    AnimatedError(errorMessage = errorMessage)
                }
            } else null,
        chipLabel = chipLabel,
        interactionSource = interactionSource,
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
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    enabled: Boolean = true,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    singleLine: Boolean = false,
    textFieldLabel: @Composable (() -> Unit)? = null,
    textFieldPlaceholder: @Composable (() -> Unit)? = null,
    textFieldLeadingIcon: @Composable (() -> Unit)? = null,
    textFieldTrailingIcon: @Composable (() -> Unit)? = null,
    chipLabel: @Composable (T) -> Unit = { x -> Text(displayWith(x)) },
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    OutlinedChipTextField(
        value = value,
        onValueChange = onValueChange,
        chips = chips,
        displayWith = displayWith,
        onRemoveChip = onRemoveChip,
        modifier = modifier,
        chipOverflow = chipOverflow,
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
                    AnimatedError(errorMessage = errorMessage)
                }
            } else null,
        chipLabel = chipLabel,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}
