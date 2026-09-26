package com.github.ravenzip.bereza.core.components.textfield

import androidx.compose.animation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.bereza.core.components.text.CounterLabel
import com.github.ravenzip.bereza.core.data.ComponentErrorState
import com.github.ravenzip.bereza.core.data.unwrapErrorMessage
import com.github.ravenzip.bereza.core.utils.canAddCharacter

/**
 * @param reserveSupportingContentSpace резервирует место под дополнительный контент снизу
 *   текстового поля. Если false, то компонент сам вычисляет исходя из [showTextLengthCounter],
 *   текущего [value] и [showTextLengthCounterIfZero] или исходя из [errorState], а затем анимирует
 *   вычисленный контент
 */
@Composable
fun TextFieldWithSupportingRow(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    singleLine: Boolean = false,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    Box(modifier = Modifier.animateContentSizeIf(!reserveSupportingContentSpace)) {
        TextField(
            value = value,
            onValueChange = { x ->
                if (canAddCharacter(currentLength = x.length, maxLength = maxLength)) {
                    onValueChange(x)
                }
            },
            modifier = modifier,
            enabled = enabled,
            readOnly = readonly,
            maxLines = maxLines,
            minLines = minLines,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            supportingText =
                if (
                    reserveSupportingContentSpace ||
                        errorMessage.isNotEmpty() ||
                        showTextLengthCounter && (value.isNotEmpty() || showTextLengthCounterIfZero)
                ) {
                    {
                        TextFieldSupportingRow(
                            errorMessage = errorMessage,
                            showTextLengthCounter = showTextLengthCounter,
                            showTextLengthCounterIfZero = showTextLengthCounterIfZero,
                            value = value,
                            maxLength = maxLength,
                        )
                    }
                } else null,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors,
        )
    }
}

/**
 * @param reserveSupportingContentSpace резервирует место под дополнительный контент снизу
 *   текстового поля. Если false, то компонент сам вычисляет исходя из [showTextLengthCounter],
 *   текущего [value] и [showTextLengthCounterIfZero] или исходя из [errorState], а затем анимирует
 *   вычисленный контент
 */
@Composable
fun OutlinedTextFieldWithSupportingRow(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    singleLine: Boolean = false,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    Box(modifier = Modifier.animateContentSizeIf(!reserveSupportingContentSpace)) {
        OutlinedTextField(
            value = value,
            onValueChange = { x ->
                if (canAddCharacter(currentLength = x.length, maxLength = maxLength)) {
                    onValueChange(x)
                }
            },
            modifier = modifier,
            enabled = enabled,
            readOnly = readonly,
            maxLines = maxLines,
            minLines = minLines,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            supportingText =
                if (
                    reserveSupportingContentSpace ||
                        errorMessage.isNotEmpty() ||
                        showTextLengthCounter && (value.isNotEmpty() || showTextLengthCounterIfZero)
                ) {
                    {
                        TextFieldSupportingRow(
                            errorMessage = errorMessage,
                            showTextLengthCounter = showTextLengthCounter,
                            showTextLengthCounterIfZero = showTextLengthCounterIfZero,
                            value = value,
                            maxLength = maxLength,
                        )
                    }
                } else null,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors,
        )
    }
}

private fun Modifier.animateContentSizeIf(condition: Boolean) =
    if (condition) animateContentSize() else this

@Composable
internal fun AnimatedError(errorMessage: String) {
    AnimatedVisibility(
        visible = errorMessage.isNotEmpty(),
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut(),
    ) {
        CompositionLocalProvider(LocalTextStyle provides TextFieldSupportRowStyle) {
            Text(text = errorMessage)
        }
    }
}

@Composable
private fun TextFieldSupportingRow(
    errorMessage: String,
    showTextLengthCounter: Boolean,
    showTextLengthCounterIfZero: Boolean,
    value: String,
    maxLength: Int?,
) {
    val minHeight = rememberSupportingTextHeight()

    /**
     * minHeight нужно вычислить для того, чтобы корректно среагировать на появление анимированного
     * контента. [Row] сразу будет отрисован, тогда как [AnimatedError] и [CounterLabel]
     * отрисовываются по условию, которое не факт, что в момент отображения [Row] выполнено
     */
    Row(
        modifier = Modifier.fillMaxWidth().heightIn(minHeight),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AnimatedError(errorMessage = errorMessage)

        AnimatedVisibility(
            visible = showTextLengthCounter && (value.isNotEmpty() || showTextLengthCounterIfZero),
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(),
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                CounterLabel(
                    current = value.length,
                    max = maxLength,
                )
            }
        }
    }
}

@Composable
private fun rememberSupportingTextHeight(): Dp {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    val result =
        textMeasurer.measure(
            text = "M",
            style = TextStyle(fontSize = 12.sp),
        )

    return with(density) {
        result.size.height.toDp()
    }
}

internal val TextFieldSupportRowStyle = TextStyle(fontSize = 12.sp)
