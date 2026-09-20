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
import com.github.ravenzip.berezaUI.screen.components.button.RichButtonScreen
import com.github.ravenzip.berezaUI.screen.components.checkbox.CheckboxGroupScreen
import com.github.ravenzip.berezaUI.screen.components.checkbox.CheckboxWithTextScreen
import com.github.ravenzip.berezaUI.screen.components.radiobutton.RadioButtonWithTextScreen
import com.github.ravenzip.berezaUI.screen.components.radiobutton.RadioGroupScreen
import com.github.ravenzip.berezaUI.screen.components.switch.SwitchGroupScreen
import com.github.ravenzip.berezaUI.screen.components.switch.SwitchWithTextScreen
import com.github.ravenzip.berezaUI.screen.components.textfield.*
import com.github.ravenzip.berezaUI.screen.layout.ExpandableCardScreen
import com.github.ravenzip.berezaUI.screen.layout.HorizontalPagerWithIndicatorScreen
import com.github.ravenzip.berezaUI.screen.layout.RoundedBoxScreen
import com.github.ravenzip.berezaUI.screen.layout.VerticalPagerWithIndicatorScreen
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

                    // Текстовые поля
                    is Screen.TextFieldWithSupportingRow -> {
                        TextFieldWithSupportingRowScreen(navigationViewModel)
                    }

                    is Screen.Select -> {
                        SelectScreen(navigationViewModel)
                    }

                    is Screen.MultiSelect -> {
                        MultiSelectScreen(navigationViewModel)
                    }

                    is Screen.ComboBox -> {
                        ComboboxScreen(navigationViewModel)
                    }

                    is Screen.Autocomplete -> {
                        AutocompleteTextFieldScreen(navigationViewModel)
                    }

                    // Чекбоксы
                    is Screen.CheckboxWithText -> {
                        CheckboxWithTextScreen(navigationViewModel)
                    }

                    is Screen.CheckboxGroup -> {
                        CheckboxGroupScreen(navigationViewModel)
                    }

                    // Свичи
                    is Screen.SwitchWithText -> {
                        SwitchWithTextScreen(navigationViewModel)
                    }

                    is Screen.SwitchGroup -> {
                        SwitchGroupScreen(navigationViewModel)
                    }

                    // Радиокнопки
                    is Screen.RadioButtonWithText -> {
                        RadioButtonWithTextScreen(navigationViewModel)
                    }

                    is Screen.RadioGroup -> {
                        RadioGroupScreen(navigationViewModel)
                    }

                    // Кнопки
                    is Screen.RichButton -> {
                        RichButtonScreen(navigationViewModel)
                    }

                    // Лейауты
                    is Screen.ExpandableCard -> {
                        ExpandableCardScreen(navigationViewModel)
                    }

                    is Screen.RoundedBox -> {
                        RoundedBoxScreen(navigationViewModel)
                    }

                    is Screen.HorizontalPagerWithIndicator -> {
                        HorizontalPagerWithIndicatorScreen(navigationViewModel)
                    }

                    is Screen.VerticalPagerWithIndicator -> {
                        VerticalPagerWithIndicatorScreen(navigationViewModel)
                    }

                    // Формы
                    is Screen.Login -> {
                        LoginScreen(navigationViewModel)
                    }

                    is Screen.Profile -> {
                        ProfileScreen(navigationViewModel)
                    }

                    // Инфа по последнему апдейту
                    is Screen.LastChanges -> {
                        LastChangesScreen(navigationViewModel)
                    }
                }
            }
        },
    )
}
