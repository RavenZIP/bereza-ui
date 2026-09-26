package com.github.ravenzip.berezaUI.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

// TODO почему-то не передается цвет для иконки, хотя в оригинальной M3 все также. Надо бы
// разобраться потом, не критично
@Composable
fun Chip(
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    border: BorderStroke? = SuggestionChipDefaults.suggestionChipBorder(enabled),
    horizontalArrangement: Arrangement.Horizontal = SuggestionChipDefaults.horizontalArrangement(),
    contentPadding: PaddingValues = SuggestionChipDefaults.ContentPadding,
    shape: Shape = SuggestionChipDefaults.shape,
    colors: ChipColors = SuggestionChipDefaults.suggestionChipColors(),
) {
    CompositionLocalProvider(
        LocalContentColor provides colors.labelColor(enabled),
        LocalTextStyle provides MaterialTheme.typography.labelMedium,
    ) {
        Surface(
            modifier = modifier,
            shape = shape,
            border = border,
            color = colors.containerColor(enabled),
        ) {
            Row(
                modifier =
                    Modifier.width(IntrinsicSize.Max)
                        .defaultMinSize(minHeight = InputChipDefaults.Height)
                        .padding(contentPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = horizontalArrangement,
            ) {
                if (leadingIcon != null) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.leadingIconContentColor(enabled),
                        content = leadingIcon,
                    )
                } else {
                    Spacer(modifier = Modifier.width(0.dp))
                }

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    content = { label() },
                )

                if (trailingIcon != null) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.trailingIconContentColor(enabled),
                        content = trailingIcon,
                    )
                } else {
                    Spacer(modifier = Modifier.width(0.dp))
                }
            }
        }
    }
}

internal fun ChipColors.containerColor(enabled: Boolean): Color =
    if (enabled) containerColor else disabledContainerColor

internal fun ChipColors.leadingIconContentColor(enabled: Boolean): Color =
    if (enabled) leadingIconContentColor else disabledLeadingIconContentColor

internal fun ChipColors.trailingIconContentColor(enabled: Boolean): Color =
    if (enabled) trailingIconContentColor else disabledTrailingIconContentColor

internal fun ChipColors.labelColor(enabled: Boolean): Color =
    if (enabled) labelColor else disabledLabelColor
