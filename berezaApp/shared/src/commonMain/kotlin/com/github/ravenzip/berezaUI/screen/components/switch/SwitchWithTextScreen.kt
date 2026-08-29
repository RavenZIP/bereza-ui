package com.github.ravenzip.berezaUI.screen.components.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.switch.SwitchWithText
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun SwitchWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }
    val secondControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "SwitchWithText",
        description = "Переключатель, аналогичный Switch из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        SwitchWithText(
                            control = firstControl,
                            text = { Text("С текстом") },
                        )

                        SwitchWithText(
                            control = secondControl,
                            label = "С заголовком",
                            description = "И описанием",
                            descriptionStyle = TextStyle(),
                        )
                    }
                }
            }
        },
    )
}
