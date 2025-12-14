package components.textfield.singleLine

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import components.textfield.base.BasicTextField
import components.utils.canAddCharacter
import components.utils.collectAsSnapshotStateList
import components.utils.collectAsStateLifecycleAware
import data.isEnabled
import data.isInvalid
import form.MutableFormControl

@Composable
fun SingleLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    isReadonly: Boolean = false,
    isInvalid: Boolean = false,
    errorMessage: String = "",
    onFocusChange: (FocusState) -> Unit = {},
    modifier: Modifier = Modifier,
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    isHiddenText: Boolean = false,
    showTextLengthCounter: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isEnabled = isEnabled,
        isReadonly = isReadonly,
        errorMessage = errorMessage,
        onFocusChange = onFocusChange,
        maxLength = maxLength,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isInvalid = isInvalid,
        isHiddenText = isHiddenText,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        showTextLengthCounter = showTextLengthCounter,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> SingleLineTextField(
    control: MutableFormControl<String>,
    modifier: Modifier = Modifier,
    isReadonly: Boolean = false,
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    isHiddenText: Boolean = false,
    showTextLengthCounter: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val value = control.valueChanges.collectAsStateLifecycleAware().value
    val status = control.statusChanges.collectAsStateLifecycleAware().value
    val errorMessage = control.errorMessagesChanges.collectAsSnapshotStateList().firstOrNull() ?: ""

    SingleLineTextField(
        value = value,
        onValueChange = { newValue ->
            if (canAddCharacter(currentLength = newValue.length, maxLength = maxLength)) {
                control.setValue(newValue)
            }
        },
        isEnabled = status.isEnabled(),
        isReadonly = isReadonly,
        isInvalid = status.isInvalid(),
        errorMessage = errorMessage,
        onFocusChange = { focusState ->
            if (focusState.isFocused && !control.touched) control.markAsTouched()
        },
        modifier = modifier,
        maxLength = maxLength,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isHiddenText = isHiddenText,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        shape = shape,
        colors = colors,
    )
}
