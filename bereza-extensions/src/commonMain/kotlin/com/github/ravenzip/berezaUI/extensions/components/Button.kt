package com.github.ravenzip.berezaUI.extensions.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.core.components.IconButton
import com.github.ravenzip.berezaUI.core.components.RichButton
import com.github.ravenzip.berezaUI.extensions.style.IconStyle

@Composable
fun SimpleButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    isEnabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(14.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        colors = colors,
        shape = shape,
        contentPadding = contentPadding,
    ) {
        // Игнорируем textStyle.color, потому что цветом будет управлять сама кнопка при помощи
        // contentColor
        Text(text = text, style = textStyle.merge(color = colors.contentColor))
    }
}

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
    val contentColor = if (isEnabled) color else colors.disabledContentColor

    IconButton(
        onClick = onClick,
        icon = {
            Icon(
                painter = icon,
                contentDescription = iconDescription,
                modifier = Modifier.size(iconStyle.size),
                tint = contentColor,
            )
        },
        modifier = modifier,
        isEnabled = isEnabled,
        shape = shape,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
    )
}

// TODO: Разработать дефолтные стили
@Composable
fun RichButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    label: String,
    labelStyle: TextStyle =
        TextStyle.Default.merge(fontSize = 18.sp, fontWeight = FontWeight.Medium),
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
