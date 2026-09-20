package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.Autocomplete
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class AutocompleteScreenViewModel : ViewModel() {
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

    // Имитация загрузки с сервера
    fun getSamples(query: String): Flow<List<Sample>> = flow {
        delay(2.seconds)
        emit(source.filter { it.name.startsWith(query, true) })
    }
}

@Composable
fun AutocompleteScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: AutocompleteScreenViewModel = remember {
        AutocompleteScreenViewModel()
    },
) {
    val selected by screenViewModel.selected.collectAsState()

    ComponentScreen(
        title = "Autocomplete",
        description = "Текстовое поле с выпадающим списком и автодополнением.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Autocomplete(
                    selected = selected,
                    displayWith = { x -> x.name },
                    onSelect = { x -> screenViewModel.selected.update { x } },
                    search = { x -> screenViewModel.getSamples(x) },
                    key = { x -> x.id },
                    onClear = { screenViewModel.selected.update { null } },
                )
            }
        },
    )
}
