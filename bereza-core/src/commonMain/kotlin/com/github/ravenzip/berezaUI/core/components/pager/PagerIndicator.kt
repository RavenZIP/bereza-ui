package com.github.ravenzip.berezaUI.core.components.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.style.PagerIndicatorStyle

@Composable
private fun PagerIndicator(
    pagerState: PagerState,
    selectedIndicatorConfig: PagerIndicatorStyle,
    unselectedIndicatorConfig: PagerIndicatorStyle,
) {
    repeat(pagerState.pageCount) { page ->
        val config =
            if (pagerState.currentPage == page) selectedIndicatorConfig
            else unselectedIndicatorConfig

        Box(
            modifier =
                Modifier.padding(2.dp)
                    .clip(config.shape)
                    .background(config.color)
                    .size(height = config.height.dp, width = config.width.dp)
        )
    }
}

@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    selectedIndicatorConfig: PagerIndicatorStyle = PagerIndicatorStyle.SelectedHorizontalRectangle,
    unselectedIndicatorConfig: PagerIndicatorStyle =
        PagerIndicatorStyle.UnselectedHorizontalRectangle,
    spaceBetweenIndicators: Dp = 10.dp,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenIndicators),
    ) {
        PagerIndicator(pagerState, selectedIndicatorConfig, unselectedIndicatorConfig)
    }
}

@Composable
fun VerticalPagerIndicator(
    pagerState: PagerState,
    selectedIndicatorConfig: PagerIndicatorStyle = PagerIndicatorStyle.SelectedVerticalRectangle,
    unselectedIndicatorConfig: PagerIndicatorStyle =
        PagerIndicatorStyle.UnselectedVerticalRectangle,
    spaceBetweenIndicators: Dp = 10.dp,
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(spaceBetweenIndicators),
    ) {
        PagerIndicator(pagerState, selectedIndicatorConfig, unselectedIndicatorConfig)
    }
}
