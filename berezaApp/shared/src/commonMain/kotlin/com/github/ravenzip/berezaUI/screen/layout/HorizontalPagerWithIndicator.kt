package com.github.ravenzip.berezaUI.screen.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.pager.HorizontalPagerWithIndicator
import com.github.ravenzip.berezaUI.screen.components.shared.LayoutScreen
import com.github.ravenzip.compose.material3.SimpleButton
import kotlinx.coroutines.launch

@Composable
fun HorizontalPagerWithIndicatorScreen(navigationViewModel: RootNavigationViewModel) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 4 }

    // TODO перейти на собственный LayoutScreen (это временная заглушка)
    LayoutScreen(
        title = "HorizontalPagerWithIndicator",
        description =
            "HorizontalPager с индикатором общего количества страниц и подсветкой текущей активной",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            HorizontalPagerWithIndicator(pagerState, modifier = Modifier.size(500.dp)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Page: ${it + 1}")

                        SimpleButton(
                            {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                            "Следующая страница",
                        )

                        SimpleButton(
                            {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            },
                            "Предыдущая страница",
                        )
                    }
                }
            }
        },
    )
}
