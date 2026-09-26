package com.github.ravenzip.bereza.core.components.overlay

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
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
                    default = SnackbarColors.Companion.Default,
                    success = SnackbarColors.Companion.Success,
                    warning = SnackbarColors.Companion.Warning,
                    error = SnackbarColors.Companion.Error,
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
