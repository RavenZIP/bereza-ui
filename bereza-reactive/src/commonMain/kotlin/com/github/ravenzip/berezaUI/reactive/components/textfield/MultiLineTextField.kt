package com.github.ravenzip.berezaUI.reactive.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.basic.BasicOutlinedTextField
import com.github.ravenzip.berezaUI.core.components.textfield.basic.BasicTextField
import com.github.ravenzip.berezaUI.reactive.data.collectComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun MultiLineTextField(
    control: MutableFormControl<String>,
    isReadonly: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    modifier: Modifier = Modifier,
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val state by control.collectComponentState()

    BasicTextField(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = isReadonly,
        mayHaveAnError = control.hasValidators,
        errorState = state.errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        maxLength = maxLength,
        maxLines = maxLines,
        minLines = minLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun OutlinedMultiLineTextField(
    control: MutableFormControl<String>,
    modifier: Modifier = Modifier,
    onFocusChange: (FocusState) -> Unit = {},
    isReadonly: Boolean = false,
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val state by control.collectComponentState()

    BasicOutlinedTextField(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = isReadonly,
        mayHaveAnError = control.hasValidators,
        errorState = state.errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        maxLength = maxLength,
        maxLines = maxLines,
        minLines = minLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors,
    )
}
