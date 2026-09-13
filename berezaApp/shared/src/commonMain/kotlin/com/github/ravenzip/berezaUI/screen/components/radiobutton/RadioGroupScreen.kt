package com.github.ravenzip.berezaUI.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RadioGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { mutableStateListOf("Русский", "Английский", "Прочее") }
    var selectedItem by remember { mutableStateOf("") }

    ComponentScreen(
        title = "RadioGroup",
        description = "Группа из нескольких RadioButton с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioGroup(
                source = source,
                selectedItem = selectedItem,
                onSelectionItemChange = { x -> selectedItem = x },
                content = { x -> Text(x) },
            )
        },
    )
}
