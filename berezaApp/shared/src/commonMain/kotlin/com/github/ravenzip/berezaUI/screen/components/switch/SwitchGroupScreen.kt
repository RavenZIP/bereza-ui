package com.github.ravenzip.berezaUI.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.switch.SwitchGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun SwitchGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(listOf<String>()) }

    // TODO не хардкодить названия
    ComponentScreen(
        title = "SwitchGroup",
        description = "Группа из нескольких Switch с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            SwitchGroup(
                control = firstControl,
                source = source,
                keySelector = { x -> x },
                text = { x -> Text(x) },
            )
        },
    )
}
