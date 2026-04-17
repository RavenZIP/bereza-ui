package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.basic.BasicOutlinedTextField
import com.github.ravenzip.berezaUI.core.components.textfield.basic.BasicTextField
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState

@Composable
fun SingleLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readonly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    maxLength: Int? = null,
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
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readonly = readonly,
        errorState = errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = onTouchChange,
        maxLength = maxLength,
        singleLine = true,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun OutlinedSingleLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readonly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    BasicOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readonly = readonly,
        errorState = errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = onTouchChange,
        maxLength = maxLength,
        singleLine = true,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        shape = shape,
        colors = colors,
    )
}
