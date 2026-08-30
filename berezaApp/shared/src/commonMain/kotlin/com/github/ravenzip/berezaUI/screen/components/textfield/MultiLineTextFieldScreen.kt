package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.textfield.MultiLineTextField
import com.github.ravenzip.berezaUI.reactive.components.textfield.OutlinedMultiLineTextField
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun MultiLineTextFieldScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl("") }
    val secondControl = remember { mutableFormControl("") }

    ComponentScreen(
        title = "MultiLineTextField",
        description = "Текстовое поле для ввода многострочного текста.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        MultiLineTextField(
                            control = firstControl,
                            label = { Text("MultiLineTextField") },
                        )

                        OutlinedMultiLineTextField(
                            control = secondControl,
                            label = { Text("OutlinedMultiLineTextField") },
                        )
                    }
                }
            }
        },
    )
}
