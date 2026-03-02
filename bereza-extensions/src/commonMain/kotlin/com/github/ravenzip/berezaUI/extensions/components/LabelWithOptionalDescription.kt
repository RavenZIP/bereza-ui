package com.github.ravenzip.berezaUI.extensions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
internal fun LabelWithOptionalDescription(
    label: String,
    labelStyle: TextStyle,
    description: String? = null,
    descriptionStyle: TextStyle? = null,
) {
    if (description != null && descriptionStyle != null) {
        Column {
            Text(text = label, style = labelStyle)

            Text(text = description, style = descriptionStyle)
        }
    } else {
        Text(text = label, style = labelStyle)
    }
}
