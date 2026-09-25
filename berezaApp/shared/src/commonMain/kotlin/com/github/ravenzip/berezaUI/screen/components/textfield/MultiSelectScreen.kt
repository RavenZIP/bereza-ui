package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.select.MultiSelect
import com.github.ravenzip.berezaUI.core.components.textfield.select.OutlinedMultiSelect
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

class MultiSelectScreenViewModel : ViewModel() {
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
fun MultiSelectScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: MultiSelectScreenViewModel = remember { MultiSelectScreenViewModel() },
) {
    ComponentScreen(
        title = "MultiSelect",
        description = "Текстовое поле с выпадающим списком...",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            MultiSelect(
                source = screenViewModel.source,
                selected = screenViewModel.selected,
                displayWith = { x -> x.name },
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

            OutlinedMultiSelect(
                source = screenViewModel.source,
                selected = screenViewModel.selected,
                displayWith = { x -> x.name },
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
