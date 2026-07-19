package com.github.ravenzip.berezaUI

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    val screenRouteToScreenName =
        mapOf(
            Screen.TextField to "Текстовые поля",
            Screen.Checkbox to "Чекбосксы",
            Screen.Switch to "Свичи",
            Screen.RadioButton to "Радиогруппы",
        )
    val componentScreens = screenRouteToScreenName.keys.toList()

    val formRouteToFormName =
        mapOf(
            Screen.Login to "Форма регистрации пользователя",
            Screen.Profile to "Профиль пользователя",
        )
    val formScreens = formRouteToFormName.keys.toList()

    fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    fun navigateBack() {
        backStack.remove(backStack.last())
    }
}
