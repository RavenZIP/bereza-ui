package components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import components.layout.RoundedBox
import style.IconStyle

@Composable
fun IconButton(
    onClick: () -> Unit,
    icon: Painter,
    iconDescription: String? = null,
    iconStyle: IconStyle = IconStyle.Default,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = PaddingValues(10.dp),
) {
    val color = iconStyle.color ?: colors.contentColor
    val containerColor = if (isEnabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (isEnabled) color else colors.disabledContentColor

    RoundedBox(
        modifier =
            Modifier.clickable(
                    onClick = onClick,
                    enabled = isEnabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                )
                .padding(contentPadding)
                .then(modifier),
        shape = shape,
        backgroundColor = containerColor,
    ) {
        Icon(
            painter = icon,
            contentDescription = iconDescription,
            modifier = Modifier.size(iconStyle.size),
            tint = contentColor,
        )
    }
}
