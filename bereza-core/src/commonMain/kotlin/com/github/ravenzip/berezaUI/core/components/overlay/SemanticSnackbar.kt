package com.github.ravenzip.berezaUI.core.components.overlay

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SemanticSnackbar(
    data: SnackbarData,
    colors: SemanticSnackbarColors = SemanticSnackbarColors.Default,
    showProgressBar: Boolean = true,
    actionOnNewLine: Boolean = false,
    minWidth: Dp = 400.dp,
    maxWidth: Dp = 600.dp,
    containerPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
    iconTextSpacedBy: Dp = 10.dp,
    textActionNewLineSpacedBy: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(14.dp),
    shadowElevation: Dp = 6.dp,
) {
    Snackbar(
        data = data,
        showProgressBar = showProgressBar,
        actionOnNewLine = actionOnNewLine,
        minWidth = minWidth,
        maxWidth = maxWidth,
        colors = (data.visuals as SemanticSnackbarVisuals).semantics.colors(colors),
        containerPadding = containerPadding,
        iconTextSpacedBy = iconTextSpacedBy,
        textActionNewLineSpacedBy = textActionNewLineSpacedBy,
        shape = shape,
        shadowElevation = shadowElevation,
    )
}

interface SemanticSnackbarVisuals : SnackbarVisuals {
    val semantics: SnackbarSemantics
}

private class SemanticSnackbarVisualsImpl(
    override val message: String,
    override val actionLabel: String?,
    override val withDismissAction: Boolean,
    override val duration: SnackbarDuration,
    override val icon: Painter?,
    override val semantics: SnackbarSemantics,
) : SemanticSnackbarVisuals {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SemanticSnackbarVisualsImpl

        return message == other.message &&
            actionLabel == other.actionLabel &&
            withDismissAction == other.withDismissAction &&
            duration == other.duration &&
            icon == other.icon &&
            semantics == other.semantics
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + actionLabel.hashCode()
        result = 31 * result + withDismissAction.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + semantics.hashCode()
        return result
    }
}

suspend fun SnackbarHostState.showMessage(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    icon: Painter? = null,
    semantics: SnackbarSemantics = SnackbarSemantics.Default,
): SnackbarResult =
    showMessage(
        SemanticSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            semantics,
        )
    )

suspend fun SnackbarHostState.showSuccess(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    icon: Painter? = null,
): SnackbarResult =
    showMessage(
        SemanticSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            SnackbarSemantics.Success,
        )
    )

suspend fun SnackbarHostState.showWarning(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    icon: Painter? = null,
): SnackbarResult =
    showMessage(
        SemanticSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            SnackbarSemantics.Warning,
        )
    )

suspend fun SnackbarHostState.showError(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    icon: Painter? = null,
): SnackbarResult =
    showMessage(
        SemanticSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            SnackbarSemantics.Error,
        )
    )

suspend fun SnackbarHostState.showMessage(visuals: SemanticSnackbarVisuals): SnackbarResult =
    showSnackbar(visuals)

@Immutable
data class SemanticSnackbarColors(
    val default: SnackbarColors,
    val success: SnackbarColors,
    val warning: SnackbarColors,
    val error: SnackbarColors,
) {
    companion object {
        @Stable
        val Default
            @Composable
            get() =
                SemanticSnackbarColors(
                    default = SnackbarColors.Default,
                    success = SnackbarColors.Success,
                    warning = SnackbarColors.Warning,
                    error = SnackbarColors.Error,
                )
    }
}

enum class SnackbarSemantics {
    Default,
    Success,
    Warning,
    Error,
}

private fun SnackbarSemantics.colors(colors: SemanticSnackbarColors) =
    when (this) {
        SnackbarSemantics.Default -> colors.default
        SnackbarSemantics.Success -> colors.success
        SnackbarSemantics.Warning -> colors.warning
        SnackbarSemantics.Error -> colors.error
    }
