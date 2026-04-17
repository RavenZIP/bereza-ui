package com.github.ravenzip.berezaUI

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ravenzip.berezaUI.core.components.layout.ExpandableCard
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.DropdownTextField
import com.github.ravenzip.berezaUI.core.data.SourceState
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton
import com.github.ravenzip.berezaUI.reactive.components.checkbox.CheckboxGroup
import com.github.ravenzip.berezaUI.reactive.components.checkbox.CheckboxWithText
import com.github.ravenzip.berezaUI.reactive.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.reactive.components.switch.SwitchGroup
import com.github.ravenzip.berezaUI.reactive.components.switch.SwitchWithText
import com.github.ravenzip.berezaUI.reactive.components.textfield.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.reactive.components.textfield.dropdown.AutocompleteTextField
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.Validator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class MyViewModel : ViewModel() {
    val firstTextFieldControl = mutableFormControl("", validators = listOf(Validator.required))

    val secondTextFieldControl = mutableFormControl("", validators = listOf(Validator.required))

    val checkboxControl = mutableFormControl(false)

    val checkboxGroupControl = mutableFormControl<List<Sample>>(emptyList())

    val switchControl = mutableFormControl(false)

    val firstRadioGroupControl = mutableFormControl(Sample(1, "Albert"))

    val secondRadioGroupControl = mutableFormControl(Sample(1, "Albert"))

    val switchGroupControl = mutableFormControl(emptyList<Sample>())

    val autocompleteControl = mutableFormControl(EMPTY_SAMPLE)

    val items = listOf(Sample(1, "Albert"), Sample(2, "Ivan"), Sample(3, "Nicolay"))

    val items2 =
        listOf(
            Sample(4, "Petya"),
            Sample(5, "Vasya"),
            Sample(6, "Stepan"),
            Sample(7, "Sasha"),
            Sample(8, "Viktor"),
        )

    val items3 = items + items2

    val autocompleteTextChanged = MutableSharedFlow<String>()

    var dropDownText by mutableStateOf("")
    var dropDownValue = EMPTY_SAMPLE

    val dropDownSourceState = MutableStateFlow<SourceState<Sample>>(SourceState.Idle)

    val autocompleteSourceState = MutableStateFlow<SourceState<Sample>>(SourceState.Idle)

    // TODO поиск должен выполняться только тогда, когда открыт выпадающий список? Если да, то стоит
    // ли тогда expanded как состояние поднять наверх?
    init {
        merge(autocompleteTextChanged, autocompleteControl.valueChanges.map { x -> x.name })
            .onEach { autocompleteSourceState.update { SourceState.Loading } }
            .debounce { 300L }
            .map { x ->
                val source = items3.filter { y -> y.name.startsWith(x, ignoreCase = true) }
                autocompleteSourceState.update { SourceState.Content(source) }
            }
            .launchIn(viewModelScope)

        snapshotFlow { dropDownText }
            .debounce { 300L }
            .map { x ->
                val source = items3.filter { y -> y.name.startsWith(x, ignoreCase = true) }
                dropDownSourceState.update { SourceState.Content(source) }
            }
            .launchIn(viewModelScope)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(viewModel: MyViewModel = remember { MyViewModel() }) {
    MaterialTheme {
        val expanded = remember { mutableStateOf(false) }
        val rotation = animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

        val autocompleteSourceState by viewModel.autocompleteSourceState.collectAsState()
        val dropDownSourceState by viewModel.dropDownSourceState.collectAsState()

        val coroutineScope = rememberCoroutineScope()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedSingleLineTextField(
                    control = viewModel.firstTextFieldControl,
                    label = { Text("Текстовое поле 1") },
                )

                OutlinedSingleLineTextField(
                    control = viewModel.secondTextFieldControl,
                    label = { Text("Текстовое поле 2") },
                )

                CheckboxWithText(control = viewModel.checkboxControl, label = "Я чекбокс")

                CheckboxGroup(
                    control = viewModel.checkboxGroupControl,
                    source = viewModel.items,
                    keySelector = { x -> x.id },
                    text = { x -> Text(x.name) },
                )

                SwitchWithText(
                    control = viewModel.switchControl,
                    label = "Я свитч",
                    description = "Описание",
                )

                RadioGroup(
                    control = viewModel.firstRadioGroupControl,
                    source = viewModel.items,
                    keySelector = { x -> x.id },
                    text = { x -> Text(x.name) },
                )

                RadioGroup(
                    control = viewModel.secondRadioGroupControl,
                    source = viewModel.items,
                    keySelector = { x -> x.id },
                    displayedText = { x -> x.name },
                )

                SwitchGroup(
                    control = viewModel.switchGroupControl,
                    source = viewModel.items,
                    keySelector = { x -> x.id },
                    text = { x -> Text(x.name) },
                )

                ExpandableCard(
                    text = { Text("Заголовок") },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowDownward,
                            contentDescription = "ExpandableCardArrowDownward",
                            modifier = Modifier.rotate(rotation.value),
                        )
                    },
                    onExpandedChange = { expanded.value = it },
                ) { padding ->
                    Text(
                        modifier = Modifier.padding(padding),
                        text = "Этот текст должен скрываться",
                    )
                }

                DropdownTextField(
                    sourceState = dropDownSourceState,
                    text = viewModel.dropDownText,
                    onTextChange = { viewModel.dropDownText = it },
                    onSelectItem = { x ->
                        viewModel.dropDownValue = x
                        viewModel.dropDownText = x.name
                    },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuEmptyContent = { Text("Нет результатов") },
                )

                AutocompleteTextField(
                    control = viewModel.autocompleteControl,
                    sourceState = autocompleteSourceState,
                    clearValue = EMPTY_SAMPLE,
                    itemToString = { x -> x.name },
                    onTextChange = {
                        coroutineScope.launch { viewModel.autocompleteTextChanged.emit(it) }
                    },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuEmptyContent = { Text("Нет результатов") },
                )

                SimpleButton(onClick = { viewModel.autocompleteControl.reset() }, text = "Кнопка")
            }
        }
    }
}
