package com.github.ravenzip.berezaUI

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    fun navigateBack() {
        backStack.remove(backStack.last())
    }
}
