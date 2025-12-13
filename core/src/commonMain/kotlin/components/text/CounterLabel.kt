package components.text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CounterLabel(
    modifier: Modifier = Modifier,
    max: Int? = null,
    current: Int,
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = if (max != null) "$current / $max" else "$current",
        modifier = modifier,
        style =
            style.merge(
                color = color,
                fontSize = fontSize,
                textAlign = textAlign ?: TextAlign.Unspecified,
            ),
    )
}
