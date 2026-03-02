package com.github.ravenzip.berezaUI.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.layout.RoundedBox

@Composable
fun IconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = PaddingValues(10.dp),
) {

    val containerColor = if (isEnabled) colors.containerColor else colors.disabledContainerColor

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
        icon()
    }
}

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
