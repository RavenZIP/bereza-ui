package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.data.SourceState
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.reactive.components.textfield.dropdown.AutocompleteTextField
import com.github.ravenzip.berezaUI.reactive.components.textfield.dropdown.OutlinedAutocompleteTextField
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
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

    val firstAutocompleteControl = mutableFormControl(EMPTY_SAMPLE)
    val secondAutocompleteControl = mutableFormControl(EMPTY_SAMPLE)

    val firstSourceState =
        MutableStateFlow<SourceState<Sample>>(SourceState.Content(items = source))
    val secondSourceState =
        MutableStateFlow<SourceState<Sample>>(SourceState.Content(items = source))

    val firstAutocompleteTextChanged = MutableSharedFlow<String>()
    val secondAutocompleteTextChanged = MutableSharedFlow<String>()

    /** Реализация поиска */
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
    val firstSourceState by screenViewModel.firstSourceState.collectAsState()
    val secondSourceState by screenViewModel.secondSourceState.collectAsState()

    ComponentScreen(
        title = "AutocompleteTextField",
        description = "Текстовое поле с выпадающим списком и автодополнением.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        AutocompleteTextField(
                            control = screenViewModel.firstAutocompleteControl,
                            sourceState = firstSourceState,
                            clearValue = EMPTY_SAMPLE,
                            itemToString = { x -> x.name },
                            onTextChange = {
                                coroutineScope.launch {
                                    screenViewModel.firstAutocompleteTextChanged.emit(it)
                                }
                            },
                            dropDownMenuItemContent = { x -> Text(x.name) },
                            dropDownMenuEmptyContent = { Text("Нет результатов") },
                        )

                        OutlinedAutocompleteTextField(
                            control = screenViewModel.secondAutocompleteControl,
                            sourceState = secondSourceState,
                            clearValue = EMPTY_SAMPLE,
                            itemToString = { x -> x.name },
                            onTextChange = {
                                coroutineScope.launch {
                                    screenViewModel.secondAutocompleteTextChanged.emit(it)
                                }
                            },
                            dropDownMenuItemContent = { x -> Text(x.name) },
                            dropDownMenuEmptyContent = { Text("Нет результатов") },
                        )
                    }
                }
            }
        },
    )
}
