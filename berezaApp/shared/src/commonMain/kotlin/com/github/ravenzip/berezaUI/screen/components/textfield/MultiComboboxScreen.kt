package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.MultiComboBox
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

class MultiComboboxScreenViewModel : ViewModel() {
    val source =
        listOf(
            Sample(1, "Albert"),
            Sample(2, "Ivan"),
            Sample(3, "Nicolay"),
            Sample(4, "Petya"),
            Sample(5, "Vasya"),
            Sample(6, "Stepan"),
            Sample(7, "Sasha"),
            Sample(8, "Viktor"),
        )

    val selected = mutableStateListOf<Sample>()
}

@Composable
fun MultiComboboxScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: MultiComboboxScreenViewModel = remember { MultiComboboxScreenViewModel() },
) {
    ComponentScreen(
        title = "MultiComboBox",
        description = "Текстовое поле с выпадающим списком...",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            MultiComboBox(
                source = screenViewModel.source,
                selected = screenViewModel.selected,
                displayWith = { x -> x.name },
                search = { item, text -> item.name.startsWith(text, ignoreCase = true) },
                onRemoveChip = { x ->
                    val index = screenViewModel.selected.indexOfFirst { it.name == x.name }
                    if (index != -1) screenViewModel.selected.removeAt(index)
                },
                onSelect = { x ->
                    val index = screenViewModel.selected.indexOfFirst { it.name == x.name }
                    if (index != -1) screenViewModel.selected.removeAt(index)
                    else screenViewModel.selected.add(x)
                },
                key = { x -> x.id },
            )
        },
    )
}
