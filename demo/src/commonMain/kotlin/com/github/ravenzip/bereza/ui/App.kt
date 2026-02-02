package com.github.ravenzip.bereza.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.Validator
import components.button.SimpleButton
import components.checkbox.Checkbox
import components.textfield.singleLine.OutlinedSingleLineTextField

@Composable
fun App() {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        val control1 = remember {
            mutableFormControl("", validators = listOf(Validator.required), coroutineScope = scope)
        }

        val control2 = remember {
            mutableFormControl("", validators = listOf(Validator.required), coroutineScope = scope)
        }

        val control3 = remember { mutableFormControl(false, coroutineScope = scope) }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedSingleLineTextField(control1, label = { Text("Текстовое поле 1") })

                OutlinedSingleLineTextField(control2, label = { Text("Текстовое поле 2") })

                Checkbox(control = control3, text = "Я чекбокс")

                SimpleButton(onClick = { control1.setValue("Значение") }, text = "Кнопка")
            }
        }
    }
}
