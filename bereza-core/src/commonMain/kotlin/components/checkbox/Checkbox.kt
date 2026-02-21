package components.checkbox

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun Checkbox(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    val isSelected = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

    Checkbox(
        checked = isSelected,
        onCheckedChange = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = status.isEnabled(),
        colors = colors,
    )
}
