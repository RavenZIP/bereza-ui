package components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl

@Composable
fun Checkbox(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    Row(
        modifier =
            modifier.clip(shape).clickable(enabled = enabled, onClick = onClick).padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Checkbox(checked = isSelected, onCheckedChange = null, enabled = enabled, colors = colors)

        text()
    }
}

@Composable
fun Checkbox(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle.Default.merge(fontSize = 18.sp),
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    Checkbox(
        isSelected = isSelected,
        onClick = onClick,
        modifier = modifier,
        text = { Text(text = text, style = textStyle) },
        enabled = enabled,
        colors = colors,
        padding = padding,
        shape = shape,
    )
}

@Composable
fun Checkbox(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val isSelected = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

    Checkbox(
        isSelected = isSelected,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        text = text,
        enabled = status.isEnabled(),
        colors = colors,
        padding = padding,
        shape = shape,
    )
}

@Composable
fun Checkbox(
    control: MutableFormControl<Boolean>,
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle.Default.merge(fontSize = 18.sp),
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val isSelected = control.valueChanges.collectAsState().value
    val status = control.statusChanges.collectAsState().value

    Checkbox(
        isSelected = isSelected,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        enabled = status.isEnabled(),
        colors = colors,
        padding = padding,
        shape = shape,
    )
}
