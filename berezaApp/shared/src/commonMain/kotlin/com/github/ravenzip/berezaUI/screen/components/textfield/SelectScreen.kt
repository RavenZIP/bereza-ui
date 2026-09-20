package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.OutlinedSelect
import com.github.ravenzip.berezaUI.core.components.textfield.Select
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SelectScreenViewModel : ViewModel() {
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

    val selected = MutableStateFlow<Sample?>(null)
}

@Composable
fun SelectScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: SelectScreenViewModel = remember { SelectScreenViewModel() },
) {
    val selected by screenViewModel.selected.collectAsState()

    ComponentScreen(
        title = "Select",
        description = "Текстовое поле с выпадающим списком...",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Select(
                source = screenViewModel.source,
                selected = selected,
                displayWith = { x -> x.name },
                onSelect = { x -> screenViewModel.selected.update { x } },
                key = { x -> x.id },
                onClear = { screenViewModel.selected.update { null } },
            )

            OutlinedSelect(
                source = screenViewModel.source,
                selected = selected,
                displayWith = { x -> x.name },
                onSelect = { x -> screenViewModel.selected.update { x } },
                key = { x -> x.id },
                onClear = { screenViewModel.selected.update { null } },
            )
        },
    )
}
