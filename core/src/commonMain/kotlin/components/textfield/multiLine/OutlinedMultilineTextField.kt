package components.textfield.multiLine

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import components.textfield.base.BasicOutlinedTextField
import components.utils.collectAsSnapshotStateList
import components.utils.collectAsStateLifecycleAware
import data.ComponentErrorState

@Composable
fun OutlinedMultiLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isReadonly: Boolean = false,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    BasicOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isEnabled = isEnabled,
        isReadonly = isReadonly,
        errorState = errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = onTouchChange,
        maxLength = maxLength,
        maxLines = maxLines,
        minLines = minLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        showTextLengthCounter = showTextLengthCounter,
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    showTextLengthCounter: Boolean = false,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val value = control.valueChanges.collectAsStateLifecycleAware().value
    val status = control.statusChanges.collectAsStateLifecycleAware().value
    val dirty = control.dirtyChanges.collectAsStateLifecycleAware().value
    val touched = control.touchedChanges.collectAsStateLifecycleAware().value
    val errorMessage = control.errorMessagesChanges.collectAsSnapshotStateList().firstOrNull() ?: ""
    val errorState =
        remember(status, dirty, touched) {
            if (status.isInvalid() && (dirty || touched)) ComponentErrorState.Error(errorMessage)
            else ComponentErrorState.Ok
        }

    BasicOutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        isEnabled = status.isEnabled(),
        isReadonly = isReadonly,
        errorState = errorState,
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
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors,
    )
}
