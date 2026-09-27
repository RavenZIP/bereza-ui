package com.github.ravenzip.bereza.app.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.radio.RadioButton

@Composable
fun RadioButtonScreen(navigationViewModel: RootNavigationViewModel) {
    var selected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "RadioButton",
        description = "Радиокнопка, аналогичная RadioButton из Material 3, но TODO",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioButton(
                selected = selected,
                onClick = { selected = !selected },
                content = { Text("С текстом") },
            )
        },
    )
}
