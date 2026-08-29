package com.github.ravenzip.berezaUI.screen.components

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
import com.github.ravenzip.berezaUI.reactive.components.checkbox.CheckboxWithText
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun CheckboxWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    val firstCheckboxControl = remember { mutableFormControl(false) }
    val secondCheckboxControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "CheckboxWithText",
        description = "Переключатель, аналогичный Checkbox из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        CheckboxWithText(
                            control = firstCheckboxControl,
                            text = { Text("С текстом") },
                        )

                        CheckboxWithText(
                            control = secondCheckboxControl,
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
