package com.github.ravenzip.berezaUI.screen.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.IconButton
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun IconButtonScreen(navigationViewModel: RootNavigationViewModel) {
    ComponentScreen(
        title = "IconButton",
        description = "Кнопка с иконкой",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            IconButton(
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Call,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                },
            )
        },
    )
}
