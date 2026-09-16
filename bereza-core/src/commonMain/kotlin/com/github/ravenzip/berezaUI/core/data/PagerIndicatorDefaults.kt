package com.github.ravenzip.berezaUI.core.data

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
class PagerIndicatorColors(
    val unselected: Color,
    val selected: Color,
)

@Immutable
class PagerIndicatorShapes(
    val unselected: Shape,
    val selected: Shape,
)

@Immutable
object PagerIndicatorDefaults {
    @Composable
    fun colors(): PagerIndicatorColors =
        PagerIndicatorColors(MaterialTheme.colorScheme.outline, MaterialTheme.colorScheme.primary)

    fun circleShapes(): PagerIndicatorShapes = PagerIndicatorShapes(CircleShape, CircleShape)

    fun roundedCornerShapes(): PagerIndicatorShapes =
        PagerIndicatorShapes(RoundedCornerShape(14.dp), RoundedCornerShape(14.dp))
}
