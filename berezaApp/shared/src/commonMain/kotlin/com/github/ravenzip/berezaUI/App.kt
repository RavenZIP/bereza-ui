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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.layout.ExpandableCard
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
import com.github.ravenzip.berezaUI.core.components.textfield.OutlinedAutocompleteTextField
import com.github.ravenzip.berezaUI.core.components.textfield.singleLine.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.core.data.autocomplete.AutocompleteSearch
import com.github.ravenzip.berezaUI.core.data.autocomplete.AutocompleteSource
import com.github.ravenzip.berezaUI.data.EMPTY_SAMPLE
import com.github.ravenzip.berezaUI.data.Sample
import com.github.ravenzip.berezaUI.extensions.components.CheckboxWithText
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton
import com.github.ravenzip.berezaUI.extensions.components.SwitchWithText
import com.github.ravenzip.berezaUI.extensions.components.radio.RadioGroup
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.Validator
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Composable
fun App() {
    MaterialTheme {
        val control1 = remember { mutableFormControl("", validators = listOf(Validator.required)) }

        val control2 = remember { mutableFormControl("", validators = listOf(Validator.required)) }

        val control3 = remember { mutableFormControl(false) }

        val control4 = remember { mutableFormControl(false) }

        val control5 = remember { mutableFormControl(Sample(1, "Albert")) }

        val control6 = remember { mutableFormControl(Sample(1, "Albert")) }

        val items = remember {
            listOf(Sample(1, "Albert"), Sample(2, "Ivan"), Sample(3, "Nicolay"))
        }

        val dropDownText = remember { mutableStateOf("") }

        val mutableItems = remember {
            mutableStateListOf(Sample(1, "Albert"), Sample(2, "Ivan"), Sample(3, "Nicolay"))
        }

        val expanded = remember { mutableStateOf(false) }

        val rotation = animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

        val dropDownSource = remember {
            mutableStateOf<AutocompleteSource<Sample>>(
                AutocompleteSource.Predefined(
                    items = mutableItems,
                    search = { source, value ->
                        AutocompleteSearch.startWith(
                            source = source,
                            value = value,
                            key = { y -> y.name },
                        )
                    },
                )
            )
        }

        val dropDownControl = remember { mutableFormControl(EMPTY_SAMPLE) }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedSingleLineTextField(control1, label = { Text("Текстовое поле 1") })

                OutlinedSingleLineTextField(control2, label = { Text("Текстовое поле 2") })

                CheckboxWithText(control = control3, label = "Я чекбокс")

                SwitchWithText(control = control4, label = "Я свитч", description = "Описание")

                RadioGroup(control5, items, { x -> x.id }, text = { x -> Text(x.name) })

                RadioGroup(control6, items, { x -> x.id }, displayedText = { x -> x.name })

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

                OutlinedAutocompleteTextField(
                    control = dropDownControl,
                    clearValue = EMPTY_SAMPLE,
                    source = dropDownSource.value,
                    sourceItemToString = { x -> x.name },
                    dropDownMenuItemContent = { x -> Text(x.name) },
                    dropDownMenuItemPlaceholder = { Text("Нет результатов") },
                )

                SimpleButton(
                    onClick = {
                        control1.setValue("Значение")
                        if (dropDownSource.value is AutocompleteSource.Predefined) {
                            dropDownSource.value =
                                AutocompleteSource.ByQuery({ x ->
                                    flowOf(mutableItems).map { y ->
                                        y.filter { y -> y.name.startsWith(x, ignoreCase = true) }
                                    }
                                })
                        } else {
                            dropDownSource.value =
                                AutocompleteSource.Predefined(
                                    items = mutableItems,
                                    search = { source, value ->
                                        AutocompleteSearch.startWith(
                                            source = source,
                                            value = value,
                                            key = { y -> y.name },
                                        )
                                    },
                                )
                        }
                    },
                    text = "Кнопка",
                )
            }
        }
    }
}
