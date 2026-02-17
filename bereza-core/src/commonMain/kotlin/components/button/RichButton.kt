package components.button

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import style.IconStyle

@Composable
fun RichButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
    description: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    isEnabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(14.dp),
    contentPadding: PaddingValues = PaddingValues(18.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        colors = colors,
        shape = shape,
        contentPadding = contentPadding,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            icon()

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                label()

                description()
            }
        }
    }
}

// TODO
// Разработать дефолтные стили
@Composable
fun RichButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle = TextStyle.Default.merge(fontSize = 18.sp, fontWeight = FontWeight.Medium),
    description: String,
    descriptionStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    icon: Painter,
    iconDescription: String? = null,
    iconStyle: IconStyle = IconStyle.Default,
    isEnabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(14.dp),
    contentPadding: PaddingValues = PaddingValues(18.dp),
) {
    val labelColor =
        if (labelStyle.color != Color.Unspecified) labelStyle.color else colors.contentColor
    val descriptionColor =
        if (descriptionStyle.color != Color.Unspecified) descriptionStyle.color
        else colors.contentColor

    val mergedLabelStyle = labelStyle.merge(color = labelColor)
    val mergedDescriptionStyle = descriptionStyle.merge(color = descriptionColor)

    RichButton(
        onClick = onClick,
        modifier = modifier,
        label = { Text(text = label, style = mergedLabelStyle) },
        description = { Text(text = description, style = mergedDescriptionStyle) },
        icon = {
            Icon(
                painter = icon,
                contentDescription = iconDescription,
                modifier = Modifier.size(iconStyle.size),
                tint = iconStyle.color ?: colors.contentColor,
            )
        },
        isEnabled = isEnabled,
        colors = colors,
        shape = shape,
        contentPadding = contentPadding,
    )
}
