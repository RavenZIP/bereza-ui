package com.github.ravenzip.berezaUI.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.radio.RadioButtonWithText
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RadioButtonWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    var selected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "RadioButtonWithText",
        description =
            "Радиокнопка, аналогичная RadioButton из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioButtonWithText(
                selected = selected,
                onClick = { selected = !selected },
                text = { Text("С текстом") },
            )
        },
    )
}
