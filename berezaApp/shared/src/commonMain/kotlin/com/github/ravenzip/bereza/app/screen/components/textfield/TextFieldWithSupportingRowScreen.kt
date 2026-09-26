package com.github.ravenzip.bereza.app.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.textfield.OutlinedTextFieldWithSupportingRow
import com.github.ravenzip.bereza.core.components.textfield.TextFieldWithSupportingRow
import com.github.ravenzip.bereza.core.data.ComponentErrorState
import com.github.ravenzip.compose.material3.SimpleButton

@Composable
fun TextFieldWithSupportingRowScreen(navigationViewModel: RootNavigationViewModel) {
    var firstValue by remember { mutableStateOf("") }
    var secondValue by remember { mutableStateOf("") }
    val errorState = remember { mutableStateOf<ComponentErrorState>(ComponentErrorState.Ok) }

    ComponentScreen(
        title = "TextFieldWithSupportingRow",
        description = "Текстовое поле для ввода однострочного текста.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                TextFieldWithSupportingRow(
                    value = firstValue,
                    onValueChange = { x -> firstValue = x },
                    label = { Text("TextFieldWithSupportingRow") },
                    showTextLengthCounter = true,
                    reserveSupportingContentSpace = false,
                )

                OutlinedTextFieldWithSupportingRow(
                    value = secondValue,
                    onValueChange = { x -> secondValue = x },
                    label = { Text("OutlinedSingleLineTextField") },
                    errorState = errorState.value,
                    reserveSupportingContentSpace = false,
                )

                SimpleButton(
                    {
                        errorState.value =
                            when (errorState.value) {
                                ComponentErrorState.Ok ->
                                    ComponentErrorState.Error("Введено неверное кол-во символов")
                                else -> ComponentErrorState.Ok
                            }
                    },
                    "Показать/скрыть ошибку",
                )
            }
        },
    )
}
