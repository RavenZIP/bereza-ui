package com.github.ravenzip.bereza.app.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.data.Sample
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.MultiAutocomplete
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.OutlinedMultiAutocomplete
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
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

    val selected = MutableStateFlow<List<Sample>>(listOf())

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
                val selected = screenViewModel.selected.collectAsState().value

                MultiAutocomplete(
                    selected = selected,
                    displayWith = { x -> x.name },
                    onSelectedChange = { event ->
                        screenViewModel.selected.update { event.selected }
                    },
                    search = { x -> screenViewModel.getSamples(x) },
                    key = { x -> x.id },
                    itemContent = { text -> Text(text) },
                    emptyContent = { Text("Не найдено") },
                    loadingContent = { Text("Загрузка...") },
                )

                MultiAutocomplete(
                    source = screenViewModel.source,
                    selected = selected,
                    displayWith = { x -> x.name },
                    search = { item, text -> item.name.startsWith(text, ignoreCase = true) },
                    onSelectedChange = { event ->
                        screenViewModel.selected.update { event.selected }
                    },
                    key = { x -> x.id },
                    itemContent = { text -> Text(text) },
                    emptyContent = { Text("Не найдено") },
                )

                OutlinedMultiAutocomplete(
                    selected = selected,
                    displayWith = { x -> x.name },
                    onSelectedChange = { event ->
                        screenViewModel.selected.update { event.selected }
                    },
                    search = { x -> screenViewModel.getSamples(x) },
                    key = { x -> x.id },
                    itemContent = { text -> Text(text) },
                    emptyContent = { Text("Не найдено") },
                    loadingContent = { Text("Загрузка...") },
                )

                OutlinedMultiAutocomplete(
                    source = screenViewModel.source,
                    selected = selected,
                    displayWith = { x -> x.name },
                    search = { item, text -> item.name.startsWith(text, ignoreCase = true) },
                    onSelectedChange = { event ->
                        screenViewModel.selected.update { event.selected }
                    },
                    key = { x -> x.id },
                    itemContent = { text -> Text(text) },
                    emptyContent = { Text("Не найдено") },
                )
            }
        },
    )
}
