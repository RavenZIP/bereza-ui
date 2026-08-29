package com.github.ravenzip.berezaUI

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    val componentScreens =
        mutableStateListOf<Screen>(
            Screen.SingleLineTextField,
            Screen.MultiLineTextField,
            Screen.Switch,
            Screen.Checkbox,
            Screen.RadioButton,
        )

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
