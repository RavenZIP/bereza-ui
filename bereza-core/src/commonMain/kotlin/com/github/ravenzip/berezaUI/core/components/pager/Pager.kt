package com.github.ravenzip.berezaUI.core.components.pager

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.data.PagerIndicatorColors
import com.github.ravenzip.berezaUI.core.data.PagerIndicatorDefaults
import com.github.ravenzip.berezaUI.core.data.PagerIndicatorShapes

@Composable
fun VerticalPagerWithIndicator(
    state: PagerState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondViewportPageCount: Int = PagerDefaults.BeyondViewportPageCount,
    pageSpacing: Dp = 0.dp,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    flingBehavior: TargetedFlingBehavior = PagerDefaults.flingBehavior(state = state),
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    pageNestedScrollConnection: NestedScrollConnection =
        PagerDefaults.pageNestedScrollConnection(state, Orientation.Vertical),
    snapPosition: SnapPosition = SnapPosition.Start,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    indicatorSize: DpSize = DpSize(20.dp, 10.dp),
    spaceBetweenIndicators: Dp = 10.dp,
    indicatorShapes: PagerIndicatorShapes = PagerIndicatorDefaults.roundedCornerShapes(),
    indicatorColors: PagerIndicatorColors = PagerIndicatorDefaults.colors(),
    pageContent: @Composable (PagerScope.(Int) -> Unit),
) {
    Box(modifier = modifier) {
        VerticalPager(
            state = state,
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            pageSize = pageSize,
            beyondViewportPageCount = beyondViewportPageCount,
            pageSpacing = pageSpacing,
            horizontalAlignment = horizontalAlignment,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled,
            reverseLayout = reverseLayout,
            key = key,
            pageNestedScrollConnection = pageNestedScrollConnection,
            snapPosition = snapPosition,
            overscrollEffect = overscrollEffect,
        ) { content ->
            pageContent(content)
        }

        VerticalPagerIndicator(
            pageCount = state.pageCount,
            currentPage = state.currentPage,
            modifier = Modifier.align(Alignment.CenterEnd),
            size = indicatorSize,
            spaceBetweenIndicators = spaceBetweenIndicators,
            shapes = indicatorShapes,
            colors = indicatorColors,
        )
    }
}

@Composable
fun HorizontalPagerWithIndicator(
    state: PagerState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondViewportPageCount: Int = PagerDefaults.BeyondViewportPageCount,
    pageSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    flingBehavior: TargetedFlingBehavior = PagerDefaults.flingBehavior(state = state),
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    pageNestedScrollConnection: NestedScrollConnection =
        PagerDefaults.pageNestedScrollConnection(state, Orientation.Vertical),
    snapPosition: SnapPosition = SnapPosition.Start,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    indicatorSize: DpSize = DpSize(20.dp, 10.dp),
    spaceBetweenIndicators: Dp = 10.dp,
    indicatorShapes: PagerIndicatorShapes = PagerIndicatorDefaults.roundedCornerShapes(),
    indicatorColors: PagerIndicatorColors = PagerIndicatorDefaults.colors(),
    pageContent: @Composable (PagerScope.(Int) -> Unit),
) {
    Box(modifier = modifier) {
        HorizontalPager(
            state = state,
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            pageSize = pageSize,
            beyondViewportPageCount = beyondViewportPageCount,
            pageSpacing = pageSpacing,
            verticalAlignment = verticalAlignment,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled,
            reverseLayout = reverseLayout,
            key = key,
            pageNestedScrollConnection = pageNestedScrollConnection,
            snapPosition = snapPosition,
            overscrollEffect = overscrollEffect,
        ) { content ->
            pageContent(content)
        }

        HorizontalPagerIndicator(
            pageCount = state.pageCount,
            currentPage = state.currentPage,
            modifier = Modifier.align(Alignment.BottomCenter),
            size = indicatorSize,
            spaceBetweenIndicators = spaceBetweenIndicators,
            shapes = indicatorShapes,
            colors = indicatorColors,
        )
    }
}
