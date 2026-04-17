package com.github.ravenzip.berezaUI.core.utils

import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color

internal fun TextFieldColors.calculateLabelColor(
    customIndicatorColor: Color? = null,
    invalid: Boolean,
    focused: Boolean,
): Color =
    when {
        invalid -> this.errorLabelColor
        focused -> customIndicatorColor ?: this.focusedIndicatorColor
        else -> this.unfocusedIndicatorColor
    }
