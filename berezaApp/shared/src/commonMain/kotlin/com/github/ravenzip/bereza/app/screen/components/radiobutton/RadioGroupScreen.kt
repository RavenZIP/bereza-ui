package com.github.ravenzip.bereza.app.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.radio.RadioGroup

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
