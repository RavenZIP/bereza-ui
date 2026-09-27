package com.github.ravenzip.bereza.app.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.checkbox.Checkbox

@Composable
fun CheckboxScreen(navigationViewModel: RootNavigationViewModel) {
    var selected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "Checkbox",
        description = "Переключатель, аналогичный Checkbox из Material 3, но TODO",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Checkbox(
                checked = selected,
                onCheckedChange = { x -> selected = x },
                content = { Text("С текстом") },
            )
        },
    )
}
