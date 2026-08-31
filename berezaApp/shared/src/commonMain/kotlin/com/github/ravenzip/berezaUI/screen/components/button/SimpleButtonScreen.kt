package com.github.ravenzip.berezaUI.screen.components.button

import androidx.compose.runtime.Composable
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun SimpleButtonScreen(navigationViewModel: RootNavigationViewModel) {
    ComponentScreen(
        title = "SimpleButton",
        description = "Кнопка с текстом",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            SimpleButton(onClick = {}, text = "Да, все кнопки назад оформлены мной")
        },
    )
}
