package components.utils

import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color

internal fun TextFieldColors.calculateLabelColor(
    customIndicatorColor: Color? = null,
    isInvalid: Boolean,
    isFocused: Boolean,
): Color =
    when {
        isInvalid -> this.errorLabelColor
        isFocused -> customIndicatorColor ?: this.focusedIndicatorColor
        else -> this.unfocusedIndicatorColor
    }
