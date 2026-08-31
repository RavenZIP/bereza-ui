package com.github.ravenzip.berezaUI.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.textfield.OutlinedSingleLineTextField
import com.github.ravenzip.berezaUI.reactive.components.textfield.SingleLineTextField
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun SingleLineTextFieldScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl("") }
    val secondControl = remember { mutableFormControl("") }

    ComponentScreen(
        title = "SingleLineTextField",
        description = "Текстовое поле для ввода однострочного текста.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SingleLineTextField(
                    control = firstControl,
                    label = { Text("SingleLineTextField") },
                )

                OutlinedSingleLineTextField(
                    control = secondControl,
                    label = { Text("OutlinedSingleLineTextField") },
                )
            }
        },
    )
}
