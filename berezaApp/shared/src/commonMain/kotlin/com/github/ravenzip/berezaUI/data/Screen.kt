package com.github.ravenzip.berezaUI.data

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

// TODO сгруппировать экраны аналогично как они сгруппированы в UI?
@Serializable
sealed interface Screen : NavKey {
    @Serializable data object Home : Screen, NavKey

    @Serializable data object SingleLineTextField : Screen, NavKey

    @Serializable data object MultiLineTextField : Screen, NavKey

    @Serializable data object CheckboxWithText : Screen, NavKey

    @Serializable data object CheckboxGroup : Screen, NavKey

    @Serializable data object SwitchWithText : Screen, NavKey

    @Serializable data object RadioButtonWithText : Screen, NavKey

    @Serializable data object Login : Screen, NavKey

    @Serializable data object Profile : Screen, NavKey

    @Serializable data object LastChanges : Screen, NavKey
}

fun createRouteNavigationConfig(): SavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Screen.Home::class, Screen.Home.serializer())
            subclass(Screen.SingleLineTextField::class, Screen.SingleLineTextField.serializer())
        }
    }
}
