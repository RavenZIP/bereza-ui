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
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.DropdownTextField
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.OutlinedDropdownTextField
import com.github.ravenzip.berezaUI.core.data.SourceState
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import kotlinx.coroutines.flow.MutableStateFlow

class DropDownTextFieldViewModel : ViewModel() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextFieldScreen(
    navigationViewModel: RootNavigationViewModel,
    screenViewModel: DropDownTextFieldViewModel = remember { DropDownTextFieldViewModel() },
) {
    val firstSourceState by screenViewModel.firstSourceState.collectAsState()
    val secondSourceState by screenViewModel.secondSourceState.collectAsState()

    ComponentScreen(
        title = "DropDownTextField",
        description = "Текстовое поле с выпадающим списком.",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        DropdownTextField(
                            sourceState = firstSourceState,
                            text = screenViewModel.firstDropDownText,
                            onTextChange = { screenViewModel.firstDropDownText = it },
                            onSelectItem = { x ->
                                screenViewModel.firstDropDownValue = x
                                screenViewModel.firstDropDownText = x.name
                            },
                            dropDownMenuItemContent = { x -> Text(x.name) },
                            dropDownMenuEmptyContent = { Text("Нет результатов") },
                        )

                        OutlinedDropdownTextField(
                            sourceState = secondSourceState,
                            text = screenViewModel.secondDropDownText,
                            onTextChange = { screenViewModel.secondDropDownText = it },
                            onSelectItem = { x ->
                                screenViewModel.secondDropDownValue = x
                                screenViewModel.secondDropDownText = x.name
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
