package com.github.ravenzip.berezaUI.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.radio.RadioButtonWithText
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RadioButtonWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    var firstIsSelected by remember { mutableStateOf(false) }

    ComponentScreen(
        title = "RadioButtonWithText",
        description =
            "Радиокнопка, аналогичная RadioButton из Material 3, но с текстовой подписью.",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        RadioButtonWithText(
                            selected = firstIsSelected,
                            onClick = { firstIsSelected = !firstIsSelected },
                            text = { Text("С текстом") },
                        )
                    }
                }
            }
        },
    )
}
