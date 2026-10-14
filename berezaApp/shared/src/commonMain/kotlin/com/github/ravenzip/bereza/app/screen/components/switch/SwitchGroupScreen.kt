package com.github.ravenzip.bereza.app.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.switch.SwitchGroup
import com.github.ravenzip.bereza.core.utils.collectAsSnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SwitchGroupScreenViewModel : ViewModel() {
    val source = listOf("Русский", "Английский", "Прочее")

    val selected = MutableStateFlow<List<String>>(listOf())
}

@Composable
fun SwitchGroupScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: SwitchGroupScreenViewModel = remember { SwitchGroupScreenViewModel() },
) {
    // TODO не хардкодить названия
    ComponentScreen(
        title = "SwitchGroup",
        description = "Группа из нескольких Switch с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            val selected = screenViewModel.selected.collectAsSnapshotStateList()

            SwitchGroup(
                source = screenViewModel.source,
                selectedItems = selected,
                onSelectedChange = { event ->
                    screenViewModel.selected.update { event.selected }
                },
                content = { x -> Text(x) },
            )
        },
    )
}
