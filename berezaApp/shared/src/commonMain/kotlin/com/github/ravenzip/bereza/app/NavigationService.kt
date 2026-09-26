package com.github.ravenzip.bereza.app

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.github.ravenzip.bereza.app.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    val componentScreens: SnapshotStateList<Screen> =
        mutableStateListOf(
            Screen.TextFieldWithSupportingRow,
            Screen.Select,
            Screen.MultiSelect,
            Screen.Autocomplete,
            Screen.MultiAutocomplete,
            Screen.SwitchWithText,
            Screen.CheckboxWithText,
            Screen.RadioButtonWithText,
            Screen.CheckboxGroup,
            Screen.RadioGroup,
            Screen.SwitchGroup,
            Screen.RichButton,
        )

    val layoutScreen =
        mutableStateListOf(
            Screen.ExpandableCard,
            Screen.RoundedBox,
            Screen.HorizontalPagerWithIndicator,
            Screen.VerticalPagerWithIndicator,
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
