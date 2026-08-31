package com.github.ravenzip.berezaUI.screen.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
            RichButton(
                label = "Заголовок",
                description = "Описание",
                icon = rememberVectorPainter(Icons.Outlined.Call),
            )
        },
    )
}
