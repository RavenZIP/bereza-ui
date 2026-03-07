package com.github.ravenzip.berezaUI.core.components.textfield.internal

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.ravenzip.berezaUI.core.components.text.CounterLabel
import com.github.ravenzip.berezaUI.core.components.text.HintText
import com.github.ravenzip.berezaUI.core.utils.calculateLabelColor

@Composable
fun BasicTextFieldSupportingRow(
    errorMessage: String,
    showTextLengthCounter: Boolean,
    showTextLengthCounterIfZero: Boolean,
    value: String,
    maxLength: Int?,
    isError: Boolean,
    isFocused: Boolean,
    colors: TextFieldColors,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        AnimatedVisibility(
            visible = errorMessage.isNotEmpty(),
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(),
        ) {
            HintText(text = errorMessage, color = colors.errorLabelColor)
        }

        AnimatedVisibility(
            visible = showTextLengthCounter && (value.isNotEmpty() || showTextLengthCounterIfZero),
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(),
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                CounterLabel(
                    current = value.length,
                    max = maxLength,
                    color = colors.calculateLabelColor(isInvalid = isError, isFocused = isFocused),
                )
            }
        }
    }
}
