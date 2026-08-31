package com.github.ravenzip.berezaUI.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.radio.RadioButtonWithText
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RadioButtonWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    var firstIsSelected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "RadioButtonWithText",
        description =
            "Радиокнопка, аналогичная RadioButton из Material 3, но с текстовой подписью.",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioButtonWithText(
                selected = firstIsSelected,
                onClick = { firstIsSelected = !firstIsSelected },
                text = { Text("С текстом") },
            )
        },
    )
}
