package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.textfield.MultiLineTextField
import com.github.ravenzip.berezaUI.core.components.textfield.OutlinedMultiLineTextField
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun MultiLineTextFieldScreen(navigationViewModel: RootNavigationViewModel) {
    var firstValue by remember { mutableStateOf("") }
    var secondValue by remember { mutableStateOf("") }

    ComponentScreen(
        title = "MultiLineTextField",
        description = "Текстовое поле для ввода многострочного текста.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                MultiLineTextField(
                    value = firstValue,
                    onValueChange = { x -> firstValue = x },
                    label = { Text("MultiLineTextField") },
                )

                OutlinedMultiLineTextField(
                    value = secondValue,
                    onValueChange = { x -> secondValue = x },
                    label = { Text("OutlinedMultiLineTextField") },
                )
            }
        },
    )
}
