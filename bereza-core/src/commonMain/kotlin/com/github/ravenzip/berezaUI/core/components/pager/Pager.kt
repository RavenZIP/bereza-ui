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
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.style.PagerIndicatorStyle

@Composable
fun VerticalHPagerWithIndicator(
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
    selectedIndicatorStyle: PagerIndicatorStyle = PagerIndicatorStyle.SelectedVerticalRectangle,
    unselectedIndicatorStyle: PagerIndicatorStyle = PagerIndicatorStyle.UnselectedVerticalRectangle,
    pageContent: @Composable (PagerScope.(Int) -> Unit),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        VerticalPager(
            state = state,
            modifier = modifier,
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

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            VerticalPagerIndicator(
                pagerState = state,
                selectedIndicatorConfig = selectedIndicatorStyle,
                unselectedIndicatorConfig = unselectedIndicatorStyle,
            )
        }
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
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    flingBehavior: TargetedFlingBehavior = PagerDefaults.flingBehavior(state = state),
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    pageNestedScrollConnection: NestedScrollConnection =
        PagerDefaults.pageNestedScrollConnection(state, Orientation.Vertical),
    snapPosition: SnapPosition = SnapPosition.Start,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    selectedIndicatorStyle: PagerIndicatorStyle = PagerIndicatorStyle.SelectedHorizontalRectangle,
    unselectedIndicatorStyle: PagerIndicatorStyle =
        PagerIndicatorStyle.UnselectedHorizontalRectangle,
    pageContent: @Composable (PagerScope.(Int) -> Unit),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        VerticalPager(
            state = state,
            modifier = modifier,
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

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            HorizontalPagerIndicator(
                pagerState = state,
                selectedIndicatorConfig = selectedIndicatorStyle,
                unselectedIndicatorConfig = unselectedIndicatorStyle,
            )
        }
    }
}
