package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.combobox.ComboBox
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ComboboxScreenViewModel : ViewModel() {
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
fun ComboboxScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: ComboboxScreenViewModel = remember { ComboboxScreenViewModel() },
) {
    val selected by screenViewModel.selected.collectAsState()

    ComponentScreen(
        title = "Combobox",
        description = "Текстовое поле с выпадающим списком...",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            ComboBox(
                source = screenViewModel.source,
                selected = selected,
                displayWith = { x -> x.name },
                onSelect = { x -> screenViewModel.selected.update { x } },
                search = { item, text -> item.name.startsWith(text, ignoreCase = true) },
                key = { x -> x.id },
                onClear = { screenViewModel.selected.update { null } },
            )
        },
    )
}
