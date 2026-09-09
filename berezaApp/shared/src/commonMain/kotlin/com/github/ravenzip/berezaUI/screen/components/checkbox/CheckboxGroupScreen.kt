package com.github.ravenzip.berezaUI.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.checkbox.CheckboxGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun CheckboxGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val selectedItems = remember { mutableStateListOf<String>() }

    ComponentScreen(
        title = "CheckboxGroup",
        description = "Группа из нескольких Checkbox с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            CheckboxGroup(
                source = source,
                selectedItems = selectedItems,
                onSelectedItemChange = { x ->
                    // TODO не дублировать
                    val newSelectedItems = selectedItems.toMutableList()
                    val existingIndex = newSelectedItems.indexOfFirst { s -> s == x }

                    if (existingIndex >= 0) newSelectedItems.removeAt(existingIndex)
                    else newSelectedItems.add(x)

                    selectedItems.clear()
                    selectedItems.addAll(newSelectedItems)
                },
                keySelector = { x -> x },
                text = { x -> Text(x) },
            )
        },
    )
}
