package com.github.ravenzip.bereza.core.components.overlay

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.ui.graphics.painter.Painter

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
