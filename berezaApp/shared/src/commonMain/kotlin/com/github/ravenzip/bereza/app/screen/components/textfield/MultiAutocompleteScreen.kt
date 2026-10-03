package com.github.ravenzip.bereza.app.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.data.Sample
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.MultiAutocomplete
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.OutlinedMultiAutocomplete
import com.github.ravenzip.bereza.core.data.SelectionChange
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class MultiAutocompleteScreenViewModel : ViewModel() {
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

    // Имитация загрузки с сервера
    fun getSamples(query: String): Flow<List<Sample>> = flow {
        delay(2.seconds)
        emit(source.filter { it.name.startsWith(query, true) })
    }
}

@Composable
fun MultiAutocompleteScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: MultiAutocompleteScreenViewModel = remember {
        MultiAutocompleteScreenViewModel()
    },
) {
    ComponentScreen(
        title = "MultiAutocomplete",
        description = "Текстовое поле с выпадающим списком и автодополнением.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                MultiAutocomplete(
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
                    search = { x -> screenViewModel.getSamples(x) },
                    key = { x -> x.id },
                    itemContent = { text -> Text(text) },
                    emptyContent = { Text("Не найдено") },
                    loadingContent = { Text("Загрузка...") },
                )

                MultiAutocomplete(
                    source = screenViewModel.source,
                    selected = screenViewModel.selected,
                    displayWith = { x -> x.name },
                    search = { item, text -> item.name.startsWith(text, ignoreCase = true) },
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

                OutlinedMultiAutocomplete(
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
                    search = { x -> screenViewModel.getSamples(x) },
                    key = { x -> x.id },
                    itemContent = { text -> Text(text) },
                    emptyContent = { Text("Не найдено") },
                    loadingContent = { Text("Загрузка...") },
                )

                OutlinedMultiAutocomplete(
                    source = screenViewModel.source,
                    selected = screenViewModel.selected,
                    displayWith = { x -> x.name },
                    search = { item, text -> item.name.startsWith(text, ignoreCase = true) },
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
            }
        },
    )
}
