package com.github.ravenzip.berezaUI.core.components.overlay

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// 1. TODO учитывать тип снэкбара для определения цвета
// 2. TODO стоит поработать над шириной. Не должно быть сильно много пространства, но и маленький
// снэкбар нужен ли? (мб убрать minWidth)
// 3. TODO не учитывается withDismissAction
@Composable
fun Snackbar(
    duration: SnackbarDuration,
    modifier: Modifier = Modifier,
    showProgressBar: Boolean = true,
    minWidth: Dp = 400.dp,
    maxWidth: Dp = 600.dp,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    containerPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
    contentSpacedBy: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(14.dp),
    shadowElevation: Dp = 6.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    val progress = remember { Animatable(1f) }

    Surface(
        modifier =
            Modifier.widthIn(
                    min = minWidth,
                    max = maxWidth,
                )
                .then(modifier),
        color = containerColor,
        contentColor = contentColor,
        shape = shape,
        shadowElevation = shadowElevation,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(containerPadding),
            verticalArrangement = Arrangement.spacedBy(contentSpacedBy),
        ) {
            content()

            if (duration != SnackbarDuration.Indefinite && showProgressBar) {
                LaunchedEffect(Unit) {
                    progress.animateTo(
                        targetValue = 0f,
                        animationSpec =
                            tween(
                                durationMillis = duration.toMs().toInt(),
                                easing = LinearEasing,
                            ),
                    )
                }

                LinearProgressIndicator(
                    progress = { progress.value },
                    color = contentColor,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun Snackbar(
    data: SnackbarData,
    showProgressBar: Boolean = true,
    actionOnNewLine: Boolean = false,
    minWidth: Dp = 400.dp,
    maxWidth: Dp = 600.dp,
    labelColor: Color? = null,
    iconColor: Color? = null,
    actionColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    containerPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
    iconTextSpacedBy: Dp = 10.dp,
    textActionNewLineSpacedBy: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(14.dp),
    shadowElevation: Dp = 6.dp,
) {
    val actionLabel = data.visuals.actionLabel
    val icon = (data.visuals as BerezaSnackbarVisuals).icon

    val actionComposable: (@Composable () -> Unit)? =
        if (actionLabel != null) {
            @Composable {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = actionColor),
                    onClick = { data.performAction() },
                    content = { Text(actionLabel) },
                )
            }
        } else {
            null
        }

    Snackbar(
        duration = data.visuals.duration,
        showProgressBar = showProgressBar,
        containerColor = containerColor,
        contentColor = contentColor,
        containerPadding = containerPadding,
        contentSpacedBy = textActionNewLineSpacedBy,
        minWidth = minWidth,
        maxWidth = maxWidth,
        shape = shape,
        shadowElevation = shadowElevation,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(iconTextSpacedBy)) {
                if (icon != null) {
                    Icon(
                        icon,
                        contentDescription = "Snackbar Icon",
                        tint = iconColor ?: contentColor,
                    )
                }

                Text(data.visuals.message, color = labelColor ?: contentColor)
            }

            if (actionComposable != null && !actionOnNewLine) {
                actionComposable()
            }
        }

        if (actionComposable != null && actionOnNewLine) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                actionComposable()
            }
        }
    }
}

// Взято из import androidx.compose.material3.SnackbarHost (там это SnackbarDuration.toMillis)
private fun SnackbarDuration.toMs(): Long {
    return when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
}
