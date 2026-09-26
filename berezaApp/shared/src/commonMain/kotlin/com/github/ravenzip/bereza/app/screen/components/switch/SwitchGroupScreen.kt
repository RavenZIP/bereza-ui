package com.github.ravenzip.bereza.app.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.switch.SwitchGroup
import com.github.ravenzip.bereza.core.data.SelectionChange

@Composable
fun SwitchGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { mutableStateListOf("Русский", "Английский", "Прочее") }
    val selectedItems = remember { mutableStateListOf<String>() }

    // TODO не хардкодить названия
    ComponentScreen(
        title = "SwitchGroup",
        description = "Группа из нескольких Switch с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            SwitchGroup(
                source = source,
                selectedItems = selectedItems,
                onSelectionItemChange = { item, selectionChange ->
                    when (selectionChange) {
                        SelectionChange.Deselect -> {
                            val existingIndex = selectedItems.indexOfFirst { s -> s == item }
                            selectedItems.removeAt(existingIndex)
                        }
                        else -> selectedItems.add(item)
                    }
                },
                content = { x -> Text(x) },
            )
        },
    )
}
