package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults.FocusedBorderThickness
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.data.*

// TODO как-то ограничить количество видимых элементов в выпадающем списке
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun <T> DropDownTextFieldBox(
    sourceState: SourceState<T>,
    onSelectItem: (T) -> Unit,
    expanded: Boolean,
    onExpandedChange: (DropDownExpandEvent) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    collapseAfterSelect: Boolean = true,
    textField: @Composable ExposedDropdownMenuBoxScope.() -> Unit,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit = emptyContent,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownMenuColors = DropDownMenuDefaults.colors(),
) {
    val menuBorder =
        if (colors.borderColor != null) BorderStroke(FocusedBorderThickness, colors.borderColor)
        else null

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded ->
            onExpandedChange(createDropDownExpandEvent(expanded = expanded))
        },
        modifier = modifier,
    ) {
        textField()

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(createDropDownExpandEvent(expanded = false)) },
            border = menuBorder,
            shape = shape,
            containerColor = colors.containerColor,
        ) {
            when (sourceState) {
                is SourceState.Loading -> {
                    DisabledDropDownMenuItem(text = loadingContent)
                }

                is SourceState.Content -> {
                    if (sourceState.items.isEmpty()) {
                        DisabledDropDownMenuItem(text = emptyContent)
                    } else {
                        sourceState.items.forEach { item ->
                            val computedKey = key(item)
                            key(computedKey) {
                                DropdownMenuItem(
                                    text = { itemContent(item) },
                                    onClick = {
                                        onSelectItem(item)

                                        if (collapseAfterSelect) {
                                            onExpandedChange(
                                                createDropDownExpandEvent(
                                                    expanded = false,
                                                    afterSelect = true,
                                                )
                                            )
                                        }
                                    },
                                    enabled = enabled,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisabledDropDownMenuItem(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: MenuItemColors = MenuDefaults.itemColors(),
    contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    DropdownMenuItem(
        text = text,
        onClick = {},
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = false,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}

@Composable
internal fun DropDownTextFieldTrailingContent(
    selected: Any?,
    expanded: Boolean,
    enabled: Boolean,
    onClear: (() -> Unit)?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (onClear != null && selected != null) {
            ClearButton(onClear, enabled)
        }

        AnimatedArrow(expanded)
    }
}

@Composable
internal fun ClearButton(onClear: () -> Unit, enabled: Boolean) {
    IconButton(
        onClick = onClear,
        enabled = enabled,
        shape = RoundedCornerShape(14.dp),
    ) {
        Icon(
            imageVector = Icons.Outlined.Clear,
            contentDescription = null,
        )
    }
}

@Composable
internal fun AnimatedArrow(expanded: Boolean) {
    val arrowRotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Icon(
        imageVector = Icons.Outlined.ArrowDropDown,
        contentDescription = null,
        modifier = Modifier.size(48.dp).padding(end = 12.dp).rotate(arrowRotation),
    )
}
