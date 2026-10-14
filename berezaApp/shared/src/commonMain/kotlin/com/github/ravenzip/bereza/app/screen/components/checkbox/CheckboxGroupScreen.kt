package com.github.ravenzip.bereza.app.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.checkbox.CheckboxGroup
import com.github.ravenzip.bereza.core.utils.collectAsSnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CheckboxGroupScreenViewModel : ViewModel() {
    val source = listOf("Русский", "Английский", "Прочее")

    val selected = MutableStateFlow<List<String>>(listOf())
}

@Composable
fun CheckboxGroupScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: CheckboxGroupScreenViewModel = remember { CheckboxGroupScreenViewModel() },
) {
    ComponentScreen(
        title = "CheckboxGroup",
        description = "Группа из нескольких Checkbox с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            val selected = screenViewModel.selected.collectAsSnapshotStateList()

            CheckboxGroup(
                source = screenViewModel.source,
                selectedItems = selected,
                onSelectionItemChange = { event ->
                    screenViewModel.selected.update { event.selected }
                },
                content = { x -> Text(x) },
            )
        },
    )
}
