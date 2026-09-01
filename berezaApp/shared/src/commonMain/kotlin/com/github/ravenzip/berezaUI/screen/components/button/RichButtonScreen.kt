package com.github.ravenzip.berezaUI.screen.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.RichButton
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
                onClick = {},
                label = { Text("Заголовок") },
                description = { Text("Описание") },
                icon = { Icon(imageVector = Icons.Outlined.Call, contentDescription = null) },
            )
        },
    )
}
