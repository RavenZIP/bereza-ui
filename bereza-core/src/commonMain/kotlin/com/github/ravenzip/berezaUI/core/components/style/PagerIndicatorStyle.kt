package com.github.ravenzip.berezaUI.core.components.style

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Stable
data class PagerIndicatorStyle(
    val height: Int,
    val width: Int,
    val color: Color,
    val shape: Shape,
) {
    companion object {
        @Stable
        val UnselectedCircle =
            PagerIndicatorStyle(
                height = 10,
                width = 10,
                color = Color.LightGray,
                shape = CircleShape,
            )

        @Stable val SelectedCircle = UnselectedCircle.copy(color = Color.DarkGray)

        @Stable
        val UnselectedSquare =
            PagerIndicatorStyle(
                height = 10,
                width = 10,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp),
            )

        @Stable val SelectedSquare = UnselectedSquare.copy(color = Color.DarkGray)

        @Stable
        val UnselectedHorizontalRectangle =
            PagerIndicatorStyle(
                height = 10,
                width = 20,
                color = Color.LightGray,
                shape = CircleShape,
            )

        @Stable
        val SelectedHorizontalRectangle = UnselectedHorizontalRectangle.copy(color = Color.DarkGray)

        @Stable
        val UnselectedVerticalRectangle =
            PagerIndicatorStyle(
                height = 20,
                width = 10,
                color = Color.LightGray,
                shape = CircleShape,
            )

        @Stable
        val SelectedVerticalRectangle = UnselectedVerticalRectangle.copy(color = Color.DarkGray)
    }
}
