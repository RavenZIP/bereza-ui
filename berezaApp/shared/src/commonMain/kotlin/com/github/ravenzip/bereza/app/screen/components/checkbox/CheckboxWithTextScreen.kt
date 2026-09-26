package com.github.ravenzip.bereza.app.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.checkbox.Checkbox

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
