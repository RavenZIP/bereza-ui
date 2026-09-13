package com.github.ravenzip.berezaUI.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.checkbox.Checkbox
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun CheckboxWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    var selected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "CheckboxWithText",
        description = "Переключатель, аналогичный Checkbox из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Checkbox(
                selected = selected,
                onClick = { selected = !selected },
                content = { Text("С текстом") },
            )
        },
    )
}
