package com.github.ravenzip.berezaUI.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.checkbox.CheckboxGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun CheckboxGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(listOf<String>()) }

    ComponentScreen(
        title = "CheckboxGroup",
        description = "Группа из нескольких Checkbox с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            CheckboxGroup(
                control = firstControl,
                source = source,
                keySelector = { x -> x },
                text = { x -> Text(x) },
            )
        },
    )
}
