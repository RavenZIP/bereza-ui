package com.github.ravenzip.berezaUI

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ravenzip.berezaUI.core.components.layout.ExpandableCard
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.AutocompleteTextField
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.DropdownTextField
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.core.data.LoadedState
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.extensions.components.CheckboxWithText
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton
import com.github.ravenzip.berezaUI.extensions.components.SwitchWithText
import com.github.ravenzip.berezaUI.extensions.components.radio.RadioGroup
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

    val switchControl = mutableFormControl(false)

    val firstRadioGroupControl = mutableFormControl(Sample(1, "Albert"))

    val secondRadioGroupControl = mutableFormControl(Sample(1, "Albert"))

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

    val mutableItems = mutableListOf<Sample>().apply { addAll(items + items2) }

    val autocompleteTextChanged = MutableSharedFlow<String>()

    var dropDownText by mutableStateOf("")

    var dropDownSource by mutableStateOf<List<Sample>>(mutableItems)

    init {
        merge(autocompleteTextChanged, autocompleteControl.valueChanges.map { x -> x.name })
            .debounce { 300L }
            .map { x -> mutableItems.filter { y -> y.name.startsWith(x, ignoreCase = true) } }
            .onEach { x -> dropDownSource = x }
            .onEach { println(dropDownSource.size) }
            .launchIn(viewModelScope)

        autocompleteControl.valueChanges
            .map { x -> x.name }
            .onEach { println(it) }
            .onEach { x -> dropDownText = x }
            .launchIn(viewModelScope)
    }
}

@Composable
fun App(viewModel: MyViewModel = remember { MyViewModel() }) {
    MaterialTheme {
        val expanded = remember { mutableStateOf(false) }
        val rotation = animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

        val autocompleteValue = remember { mutableStateOf(EMPTY_SAMPLE) }
        val autocompleteState =
            remember(viewModel.dropDownSource) { LoadedState.Data(viewModel.dropDownSource) }

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
                    loadedState = autocompleteState,
                    text = viewModel.dropDownText,
                    onTextChange = { viewModel.dropDownText = it },
                    onSelectItem = { x ->
                        autocompleteValue.value = x
                        viewModel.dropDownText = x.name
                    },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuItemPlaceholder = { Text("Нет результатов") },
                )

                AutocompleteTextField(
                    control = viewModel.autocompleteControl,
                    loadedState = autocompleteState,
                    clearValue = EMPTY_SAMPLE,
                    itemToString = { x -> x.name },
                    onTextChange = {
                        println("текст поменялся на $it")
                        coroutineScope.launch { viewModel.autocompleteTextChanged.emit(it) }
                    },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuItemPlaceholder = { Text("Нет результатов") },
                )

                SimpleButton(
                    onClick = { viewModel.autocompleteControl.setValue(viewModel.items[0]) },
                    text = "Кнопка",
                )
            }
        }
    }
}
