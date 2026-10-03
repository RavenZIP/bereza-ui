package com.github.ravenzip.bereza.app.screen.components.textfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.data.Sample
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.textfield.select.MultiSelect
import com.github.ravenzip.bereza.core.components.textfield.select.OutlinedMultiSelect
import com.github.ravenzip.bereza.core.data.SelectionChange

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
                onSelectionChange = { item, selectionChange ->
                    when (selectionChange) {
                        SelectionChange.Deselect -> {
                            val existingIndex =
                                screenViewModel.selected.indexOfFirst { s -> s == item }
                            screenViewModel.selected.removeAt(existingIndex)
                        }
                        else -> screenViewModel.selected.add(item)
                    }
                },
                key = { x -> x.id },
                itemContent = { text -> Text(text) },
                emptyContent = { Text("Не найдено") },
            )

            OutlinedMultiSelect(
                source = screenViewModel.source,
                selected = screenViewModel.selected,
                displayWith = { x -> x.name },
                onSelectionChange = { item, selectionChange ->
                    when (selectionChange) {
                        SelectionChange.Deselect -> {
                            val existingIndex =
                                screenViewModel.selected.indexOfFirst { s -> s == item }
                            screenViewModel.selected.removeAt(existingIndex)
                        }
                        else -> screenViewModel.selected.add(item)
                    }
                },
                key = { x -> x.id },
                itemContent = { text -> Text(text) },
                emptyContent = { Text("Не найдено") },
            )
        },
    )
}
