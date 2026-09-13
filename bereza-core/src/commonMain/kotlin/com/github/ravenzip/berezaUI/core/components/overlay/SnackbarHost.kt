package com.github.ravenzip.berezaUI.core.components.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    position: SnackbarHostPosition = SnackbarHostPosition.TopEnd,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) },
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            hostState,
            modifier.align(position.alignment).padding(position.padding),
            snackbar,
        )
    }
}

class SnackbarHostPosition(val alignment: Alignment, val padding: PaddingValues) {
    companion object {
        val TopStart =
            SnackbarHostPosition(Alignment.TopStart, PaddingValues(start = 10.dp, top = 10.dp))
        val TopCenter = SnackbarHostPosition(Alignment.TopCenter, PaddingValues(top = 10.dp))
        val TopEnd = SnackbarHostPosition(Alignment.TopEnd, PaddingValues(end = 10.dp, top = 10.dp))

        val BottomStart =
            SnackbarHostPosition(
                Alignment.BottomStart,
                PaddingValues(start = 10.dp, bottom = 10.dp),
            )
        val BottomCenter =
            SnackbarHostPosition(
                Alignment.BottomCenter,
                PaddingValues(bottom = 10.dp),
            )
        val BottomEnd =
            SnackbarHostPosition(Alignment.BottomEnd, PaddingValues(end = 10.dp, bottom = 10.dp))
    }
}

interface BerezaSnackbarVisuals : SnackbarVisuals {
    val icon: Painter?
    val type: SnackbarType
}

private class BerezaSnackbarVisualsImpl(
    override val message: String,
    override val actionLabel: String?,
    override val withDismissAction: Boolean,
    override val duration: SnackbarDuration,
    override val icon: Painter?,
    override val type: SnackbarType,
) : BerezaSnackbarVisuals {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BerezaSnackbarVisualsImpl

        return message == other.message &&
            actionLabel == other.actionLabel &&
            withDismissAction == other.withDismissAction &&
            duration == other.duration &&
            icon == other.icon &&
            type == other.type
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + actionLabel.hashCode()
        result = 31 * result + withDismissAction.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + type.hashCode()
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
    type: SnackbarType = SnackbarType.Default,
): SnackbarResult =
    showMessage(
        BerezaSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            type,
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
        BerezaSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            SnackbarType.Success,
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
        BerezaSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            SnackbarType.Warning,
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
        BerezaSnackbarVisualsImpl(
            message,
            actionLabel,
            withDismissAction,
            duration,
            icon,
            SnackbarType.Error,
        )
    )

suspend fun SnackbarHostState.showMessage(visuals: BerezaSnackbarVisuals): SnackbarResult =
    showSnackbar(visuals)

enum class SnackbarType {
    Default,
    Success,
    Warning,
    Error,
}
