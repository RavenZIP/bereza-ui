package com.github.ravenzip.berezaUI.screen.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.extensions.components.RichButton
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RichButtonScreen(navigationViewModel: RootNavigationViewModel) {
    ComponentScreen(
        title = "RichButton",
        description = "Кнопка с иконкой, заголовком и описанием",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Row {
                Card {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        RichButton(
                            label = "Заголовок",
                            description = "Описание",
                            icon = rememberVectorPainter(Icons.Outlined.Call),
                        )
                    }
                }
            }
        },
    )
}
