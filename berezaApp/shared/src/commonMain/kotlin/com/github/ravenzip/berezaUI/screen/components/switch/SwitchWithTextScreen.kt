package com.github.ravenzip.berezaUI.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.switch.SwitchWithText
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun SwitchWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }
    val secondControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "SwitchWithText",
        description = "Переключатель, аналогичный Switch из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            SwitchWithText(
                control = firstControl,
                text = { Text("С текстом") },
            )
        },
    )
}
