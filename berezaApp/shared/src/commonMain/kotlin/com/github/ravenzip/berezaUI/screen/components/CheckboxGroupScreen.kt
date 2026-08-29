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
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.reactive.components.checkbox.CheckboxGroup
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl

@Composable
fun CheckboxGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(listOf<String>()) }

    ComponentScreen(
        title = "CheckboxGroup",
        description = "Группа из нескольких Checkbox с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        CheckboxGroup(
                            control = firstControl,
                            source = source,
                            keySelector = { x -> x },
                            text = { x -> Text(x) },
                        )
                    }
                }
            }
        },
    )
}
