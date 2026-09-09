package com.github.ravenzip.berezaUI.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RadioGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val selectedItem = remember { mutableStateOf("") }

    ComponentScreen(
        title = "RadioGroup",
        description = "Группа из нескольких RadioButton с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioGroup(
                source = source,
                selectedItem = selectedItem.value,
                onSelectedItemChange = { x ->
                    selectedItem.value = x
                },
                keySelector = { x -> x },
                text = { x -> Text(x) },
            )
        },
    )
}
