package com.github.ravenzip.berezaUI.core.components.textfield.internal

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.unwrapErrorMessage
import com.github.ravenzip.berezaUI.core.utils.canAddCharacter

@Composable
fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    isReadonly: Boolean = false,
    mayHaveAnError: Boolean = true,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
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
    val isError = remember(errorState) { errorState is ComponentErrorState.Error }
    val errorMessage = remember(errorState) { errorState.unwrapErrorMessage() }

    FocusLostEffect(isFocused = isFocused, onFocusLost = onTouchedChange)

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
                    BasicTextFieldSupportingRow(
                        errorMessage = errorMessage,
                        showTextLengthCounter = showTextLengthCounter,
                        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
                        value = value,
                        maxLength = maxLength,
                        isError = isError,
                        isFocused = isFocused.value,
                        colors = colors,
                    )
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
