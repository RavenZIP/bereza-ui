package com.github.ravenzip.bereza.core.components.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.data.PagerIndicatorColors
import com.github.ravenzip.bereza.core.data.PagerIndicatorDefaults
import com.github.ravenzip.bereza.core.data.PagerIndicatorShapes

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    size: DpSize = DpSize(10.dp, 20.dp),
    shapes: PagerIndicatorShapes = PagerIndicatorDefaults.roundedCornerShapes(),
    colors: PagerIndicatorColors = PagerIndicatorDefaults.colors(),
) {

    ButtonDefaults.buttonColors()
    repeat(pageCount) { page ->
        val selected = page == currentPage
        val color = if (selected) colors.selected else colors.unselected
        val shape = if (selected) shapes.selected else shapes.unselected

        Box(modifier = Modifier.padding(2.dp).clip(shape).background(color).size(size))
    }
}

@Composable
fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(20.dp, 10.dp),
    spaceBetweenIndicators: Dp = 10.dp,
    shapes: PagerIndicatorShapes = PagerIndicatorDefaults.roundedCornerShapes(),
    colors: PagerIndicatorColors = PagerIndicatorDefaults.colors(),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenIndicators),
    ) {
        PagerIndicator(
            pageCount = pageCount,
            currentPage = currentPage,
            size = size,
            shapes = shapes,
            colors = colors,
        )
    }
}

@Composable
fun VerticalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(10.dp, 20.dp),
    spaceBetweenIndicators: Dp = 10.dp,
    shapes: PagerIndicatorShapes = PagerIndicatorDefaults.roundedCornerShapes(),
    colors: PagerIndicatorColors = PagerIndicatorDefaults.colors(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spaceBetweenIndicators),
    ) {
        PagerIndicator(
            pageCount = pageCount,
            currentPage = currentPage,
            size = size,
            shapes = shapes,
            colors = colors,
        )
    }
}
