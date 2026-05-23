package com.github.ravenzip.berezaUI

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.github.ravenzip.berezaUI.data.Screen
import com.github.ravenzip.berezaUI.data.createRouteNavigationConfig
import com.github.ravenzip.berezaUI.screen.CheckboxScreen
import com.github.ravenzip.berezaUI.screen.HomeScreen

@Composable
fun RootNavigation(
    navigationViewModel: RootNavigationViewModel = remember { RootNavigationViewModel() }
) {
    val backStack = rememberNavBackStack(createRouteNavigationConfig(), Screen.Home)

    NavDisplay(
        backStack = navigationViewModel.backStack,
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider = { key ->
            NavEntry(key) {
                when (key) {
                    is Screen.Home -> {
                        HomeScreen(navigationViewModel)
                    }

                    is Screen.TextField -> {
                        /** Not implemented */
                    }

                    is Screen.Checkbox -> {
                        CheckboxScreen(navigationViewModel)
                    }
                }
            }
        },
    )
}
