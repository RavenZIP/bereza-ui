package com.github.ravenzip.berezaUI.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.switch.Switch
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun SwitchWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    var selected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "SwitchWithText",
        description = "Переключатель, аналогичный Switch из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Switch(
                selected = selected,
                onClick = { selected = !selected },
                content = { Text("С текстом") },
            )
        },
    )
}
