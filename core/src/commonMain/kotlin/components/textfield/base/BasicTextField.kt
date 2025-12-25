package components.textfield.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.krex.function.pairwise
import components.layout.SupportRow
import components.text.CounterLabel
import components.text.HintText
import components.utils.calculateLabelColor
import components.utils.canAddCharacter
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    isReadonly: Boolean = false,
    isInvalid: Boolean = false,
    isDirty: Boolean = false,
    isTouched: Boolean = false,
    errorMessage: String = "",
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    val isFocused = rememberSaveable { mutableStateOf(false) }
    val shouldDisplayError = isInvalid && (isDirty || isTouched)

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
        supportingText = {
            SupportRow(
                left =
                    if (shouldDisplayError && errorMessage.isNotEmpty()) {
                        { HintText(text = errorMessage, color = colors.errorLabelColor) }
                    } else null,
                right =
                    if (showTextLengthCounter) {
                        {
                            CounterLabel(
                                current = value.length,
                                max = maxLength,
                                color =
                                    colors.calculateLabelColor(
                                        isInvalid = shouldDisplayError,
                                        isFocused = isFocused.value,
                                    ),
                            )
                        }
                    } else null,
            )
        },
        isError = shouldDisplayError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        shape = shape,
        colors = colors,
    )
}
