package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.AutocompleteTextField
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.OutlinedAutocompleteTextField
import com.github.ravenzip.berezaUI.core.data.SourceState
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AutocompleteTextFieldScreenViewModel : ViewModel() {
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

    val firstValue = MutableStateFlow(EMPTY_SAMPLE)
    val secondValue = MutableStateFlow(EMPTY_SAMPLE)

    val firstSourceState =
        MutableStateFlow<SourceState<Sample>>(SourceState.Content(items = source))
    val secondSourceState =
        MutableStateFlow<SourceState<Sample>>(SourceState.Content(items = source))

    val firstValueChanged = MutableSharedFlow<Sample>()
    val secondValueChanged = MutableSharedFlow<Sample>()

    val firstTextChanged = MutableSharedFlow<String>()
    val secondTextChanged = MutableSharedFlow<String>()

    /** Реализация поиска */
    // TODO поиск должен выполняться только тогда, когда открыт выпадающий список? Если да, то стоит
    // ли тогда expanded как состояние поднять наверх?
    //    init {
    //    merge(autocompleteTextChanged, autocompleteControl.valueChanges.map { x -> x.name })
    //    .onEach { autocompleteSourceState.update { SourceState.Loading } }
    //    .debounce { 300L }
    //    .map { x ->
    //        val source = items3.filter { y -> y.name.startsWith(x, ignoreCase = true) }
    //        autocompleteSourceState.update { SourceState.Content(source) }
    //    }
    //    .launchIn(viewModelScope)
    //    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutocompleteTextFieldScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: AutocompleteTextFieldScreenViewModel = remember {
        AutocompleteTextFieldScreenViewModel()
    },
) {
    val coroutineScope = rememberCoroutineScope()
    val firstValue by screenViewModel.firstValue.collectAsState()
    val secondValue by screenViewModel.secondValue.collectAsState()
    val firstSourceState by screenViewModel.firstSourceState.collectAsState()
    val secondSourceState by screenViewModel.secondSourceState.collectAsState()

    ComponentScreen(
        title = "AutocompleteTextField",
        description = "Текстовое поле с выпадающим списком и автодополнением.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                AutocompleteTextField(
                    selected = firstValue,
                    sourceState = firstSourceState,
                    onSelectItem = { x ->
                        coroutineScope.launch { screenViewModel.firstValueChanged.emit(x) }
                    },
                    onClearSelected = {
                        coroutineScope.launch {
                            screenViewModel.firstValueChanged.emit(EMPTY_SAMPLE)
                        }
                    },
                    itemToString = { x -> x.name },
                    onTextChange = {
                        coroutineScope.launch {
                            screenViewModel.firstTextChanged.emit(it)
                        }
                    },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuEmptyContent = { Text("Нет результатов") },
                )

                OutlinedAutocompleteTextField(
                    selected = secondValue,
                    sourceState = secondSourceState,
                    onSelectItem = { x ->
                        coroutineScope.launch { screenViewModel.secondValueChanged.emit(x) }
                    },
                    onClearSelected = {
                        coroutineScope.launch {
                            screenViewModel.secondValueChanged.emit(EMPTY_SAMPLE)
                        }
                    },
                    itemToString = { x -> x.name },
                    onTextChange = {
                        coroutineScope.launch {
                            screenViewModel.secondTextChanged.emit(it)
                        }
                    },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuEmptyContent = { Text("Нет результатов") },
                )
            }
        },
    )
}
