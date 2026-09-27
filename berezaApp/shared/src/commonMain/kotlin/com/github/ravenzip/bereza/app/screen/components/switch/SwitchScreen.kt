package com.github.ravenzip.bereza.app.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.switch.Switch

@Composable
fun SwitchScreen(navigationViewModel: RootNavigationViewModel) {
    var selected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "Switch",
        description = "Переключатель, аналогичный Switch из Material 3, но TODO",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Switch(
                checked = selected,
                onCheckedChange = { selected = !selected },
                content = { Text("С текстом") },
            )
        },
    )
}
