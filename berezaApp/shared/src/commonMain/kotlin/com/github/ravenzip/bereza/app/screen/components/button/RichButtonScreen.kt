package com.github.ravenzip.bereza.app.screen.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.RichButton

@Composable
fun RichButtonScreen(navigationViewModel: RootNavigationViewModel) {
    ComponentScreen(
        title = "RichButton",
        description = "Кнопка с иконкой, заголовком и описанием",
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
