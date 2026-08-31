package com.github.ravenzip.berezaUI.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun RadioGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(source.first()) }

    ComponentScreen(
        title = "RadioGroup",
        description = "Группа из нескольких RadioButton с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioGroup(
                control = firstControl,
                source = source,
                keySelector = { x -> x },
                text = { x -> Text(x) },
            )
        },
    )
}
