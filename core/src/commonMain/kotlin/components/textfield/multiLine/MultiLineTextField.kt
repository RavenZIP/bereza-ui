package components.textfield.multiLine

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import components.textfield.base.BasicTextField
import components.utils.collectAsSnapshotStateList
import components.utils.collectAsStateLifecycleAware

@Composable
fun MultiLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDirty: Boolean = false,
    isTouched: Boolean = false,
    isEnabled: Boolean = true,
    isInvalid: Boolean = false,
    isReadonly: Boolean = false,
    errorMessage: String = "",
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
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isEnabled = isEnabled,
        isReadonly = isReadonly,
        isInvalid = isInvalid,
        isDirty = isDirty,
        isTouched = isTouched,
        onFocusChange = onFocusChange,
        onTouchedChange = onTouchChange,
        errorMessage = errorMessage,
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    val value = control.valueChanges.collectAsStateLifecycleAware().value
    val status = control.statusChanges.collectAsStateLifecycleAware().value
    val dirty = control.dirtyChanges.collectAsStateLifecycleAware().value
    val touched = control.touchedChanges.collectAsStateLifecycleAware().value
    val errorMessage = control.errorMessagesChanges.collectAsSnapshotStateList().firstOrNull() ?: ""

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        isEnabled = status.isEnabled(),
        isReadonly = isReadonly,
        isInvalid = status.isInvalid(),
        isDirty = dirty,
        isTouched = touched,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        errorMessage = errorMessage,
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
