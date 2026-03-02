package com.github.ravenzip.berezaUI.core.components.textfield.base

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.data.unwrapErrorMessage
import com.github.ravenzip.berezaUI.core.utils.calculateLabelColor
import com.github.ravenzip.berezaUI.core.utils.canAddCharacter
import com.github.ravenzip.krex.function.pairwise
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    isReadonly: Boolean = false,
    mayHaveAnError: Boolean = true,
    errorState: com.github.ravenzip.berezaUI.core.data.ComponentErrorState =
        _root_ide_package_.com.github.ravenzip.berezaUI.core.data.ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchedChange: () -> Unit,
    modifier: Modifier = Modifier,
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
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val isFocused = rememberSaveable { mutableStateOf(false) }
    val isError =
        remember(errorState) {
            errorState is com.github.ravenzip.berezaUI.core.data.ComponentErrorState.Error
        }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    LaunchedEffect(Unit) {
        snapshotFlow { isFocused.value }
            .pairwise()
            .filter { x -> !x.second }
            .onEach { onTouchedChange() }
            .launchIn(this)
    }

    TextField(
        value = value,
        onValueChange = { x ->
            if (canAddCharacter(currentLength = x.length, maxLength = maxLength)) {
                onValueChange(x)
            }
        },
        modifier =
            modifier.onFocusChanged { x ->
                isFocused.value = x.isFocused
                onFocusChange(x)
            },
        enabled = isEnabled,
        readOnly = isReadonly,
        maxLines = maxLines,
        minLines = minLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText =
            if (mayHaveAnError || showTextLengthCounter) {
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        AnimatedVisibility(
                            visible = errorMessage.isNotEmpty(),
                            enter = slideInVertically() + fadeIn(),
                            exit = slideOutVertically() + fadeOut(),
                        ) {
                            _root_ide_package_.com.github.ravenzip.berezaUI.core.components.text
                                .HintText(text = errorMessage, color = colors.errorLabelColor)
                        }

                        AnimatedVisibility(
                            visible =
                                showTextLengthCounter &&
                                    (value.isNotEmpty() || showTextLengthCounterIfZero),
                            enter = slideInVertically() + fadeIn(),
                            exit = slideOutVertically() + fadeOut(),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd,
                            ) {
                                _root_ide_package_.com.github.ravenzip.berezaUI.core.components.text
                                    .CounterLabel(
                                        current = value.length,
                                        max = maxLength,
                                        color =
                                            colors.calculateLabelColor(
                                                isInvalid = isError,
                                                isFocused = isFocused.value,
                                            ),
                                    )
                            }
                        }
                    }
                }
            } else null,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        shape = shape,
        colors = colors,
    )
}
