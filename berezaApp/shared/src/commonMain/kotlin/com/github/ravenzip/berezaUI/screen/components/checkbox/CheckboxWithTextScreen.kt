package com.github.ravenzip.berezaUI.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.checkbox.CheckboxWithText
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun CheckboxWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "CheckboxWithText",
        description = "Переключатель, аналогичный Checkbox из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            CheckboxWithText(
                control = firstControl,
                text = { Text("С текстом") },
            )
        },
    )
}
