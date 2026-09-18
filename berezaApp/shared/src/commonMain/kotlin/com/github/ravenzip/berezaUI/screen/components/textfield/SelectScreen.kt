package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.Select
import com.github.ravenzip.berezaUI.core.data.SourceState
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
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

    val firstSourceState =
        MutableStateFlow<SourceState<Sample>>(SourceState.Content(items = source))
    val secondSourceState =
        MutableStateFlow<SourceState<Sample>>(SourceState.Content(items = source))

    var firstDropDownText by mutableStateOf("")
    var secondDropDownText by mutableStateOf("")

    var firstDropDownValue = EMPTY_SAMPLE
    var secondDropDownValue = EMPTY_SAMPLE

    /** Реализация поиска */
    //    init {
    //        snapshotFlow { firstDropDownText }
    //            .debounce { 300L }
    //            .map { x ->
    //                val source = source.filter { y -> y.name.startsWith(x, ignoreCase = true) }
    //                firstSourceState.update { SourceState.Content(source) }
    //            }
    //            .launchIn(viewModelScope)
    //    }
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
        },
    )
}
