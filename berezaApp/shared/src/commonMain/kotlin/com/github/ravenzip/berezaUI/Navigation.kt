package com.github.ravenzip.berezaUI

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.github.ravenzip.berezaUI.data.Screen
import com.github.ravenzip.berezaUI.screen.HomeScreen
import com.github.ravenzip.berezaUI.screen.LastChangesScreen
import com.github.ravenzip.berezaUI.screen.components.*
import com.github.ravenzip.berezaUI.screen.scenarios.LoginScreen
import com.github.ravenzip.berezaUI.screen.scenarios.ProfileScreen

@Composable
fun RootNavigation(
    navigationViewModel: RootNavigationViewModel = remember { RootNavigationViewModel() }
) {
    //    val backStack = rememberNavBackStack(createRouteNavigationConfig(), Screen.Home)

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

                    is Screen.SingleLineTextField -> {
                        SingleLineTextFieldScreen(navigationViewModel)
                    }

                    is Screen.MultiLineTextField -> {
                        MultiLineTextFieldScreen(navigationViewModel)
                    }

                    is Screen.Checkbox -> {
                        CheckboxScreen(navigationViewModel)
                    }

                    is Screen.Switch -> {
                        SwitchScreen(navigationViewModel)
                    }

                    is Screen.RadioButton -> {
                        RadioButtonScreen(navigationViewModel)
                    }

                    is Screen.Login -> {
                        LoginScreen(navigationViewModel)
                    }

                    is Screen.Profile -> {
                        ProfileScreen(navigationViewModel)
                    }

                    is Screen.LastChanges -> {
                        LastChangesScreen(navigationViewModel)
                    }
                }
            }
        },
    )
}
