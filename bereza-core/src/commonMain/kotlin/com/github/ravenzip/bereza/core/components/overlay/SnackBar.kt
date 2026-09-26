package com.github.ravenzip.bereza.core.components.overlay

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// TODO стоит поработать над шириной. Не должно быть сильно много пространства, но и маленький
// снэкбар нужен ли? (мб убрать minWidth)
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
    colors: SnackbarColors = SnackbarColors.Default,
    containerPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
    iconTextSpacedBy: Dp = 10.dp,
    buttonSpaceBy: Dp = 10.dp,
    textActionNewLineSpacedBy: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(14.dp),
    shadowElevation: Dp = 6.dp,
) {
    val visuals = data.visuals as SnackbarVisuals
    val actionLabel = visuals.actionLabel
    val icon = visuals.icon
    val withDismissAction = visuals.withDismissAction

    val actionComposable: (@Composable () -> Unit)? =
        if (actionLabel != null) {
            @Composable {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = colors.actionColor),
                    onClick = { data.performAction() },
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Text(actionLabel)
                }
            }
        } else {
            null
        }

    val dismissActionComposable: (@Composable () -> Unit)? =
        if (withDismissAction) {
            @Composable {
                IconButton(
                    onClick = { data.dismiss() },
                    shapes = IconButtonShapes(RoundedCornerShape(14.dp)),
                ) {
                    Icon(
                        Icons.Outlined.Close,
                        contentDescription = "Snackbar Dismiss Action",
                        tint = colors.iconColor,
                    )
                }
            }
        } else {
            null
        }

    Snackbar(
        duration = visuals.duration,
        showProgressBar = showProgressBar,
        containerColor = colors.containerColor,
        contentColor = colors.progressBarColor,
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
                        tint = colors.iconColor,
                    )
                }

                Text(visuals.message, color = colors.textColor)
            }

            if (
                !actionOnNewLine && (actionComposable !== null || dismissActionComposable != null)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(buttonSpaceBy)) {
                    if (actionComposable != null) {
                        actionComposable()
                    }

                    if (dismissActionComposable != null) {
                        dismissActionComposable()
                    }
                }
            }
        }

        if (actionOnNewLine) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Row(horizontalArrangement = Arrangement.spacedBy(buttonSpaceBy)) {
                    if (actionComposable != null) {
                        actionComposable()
                    }

                    if (dismissActionComposable != null) {
                        dismissActionComposable()
                    }
                }
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

@Immutable
data class SnackbarColors(
    val containerColor: Color,
    val textColor: Color,
    val progressBarColor: Color,
    val actionColor: Color,
    val iconColor: Color,
) {
    companion object {
        @Stable
        val Default
            @Composable
            get() =
                SnackbarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    progressBarColor = MaterialTheme.colorScheme.onSurface,
                    actionColor = MaterialTheme.colorScheme.primary,
                    iconColor = MaterialTheme.colorScheme.onSurface,
                )

        @Stable
        val Success
            @Composable
            get() =
                SnackbarColors(
                    containerColor = Color(0xFFE8F5E9),
                    textColor = Color(0xFF1B5E20),
                    progressBarColor = Color(0xFF1B5E20),
                    actionColor = Color(0xFF2E7D32),
                    iconColor = Color(0xFF2E7D32),
                )

        @Stable
        val Warning
            @Composable
            get() =
                SnackbarColors(
                    containerColor = Color(0xFFFFF8E1),
                    textColor = Color(0xFF5D4037),
                    progressBarColor = Color(0xFF5D4037),
                    actionColor = Color(0xFFF57F17),
                    iconColor = Color(0xFFF57F17),
                )

        @Stable
        val Error
            @Composable
            get() =
                SnackbarColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    textColor = MaterialTheme.colorScheme.onErrorContainer,
                    progressBarColor = MaterialTheme.colorScheme.onErrorContainer,
                    actionColor = MaterialTheme.colorScheme.error,
                    iconColor = MaterialTheme.colorScheme.error,
                )
    }
}
