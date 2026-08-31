package com.github.ravenzip.berezaUI.screen.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.layout.RoundedBox
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun RoundedBoxScreen(navigationViewModel: RootNavigationViewModel) {
    // TODO перейти на собственный LayoutScreen
    ComponentScreen(
        title = "RoundedBox",
        description = "Box со скругленными углами.",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RoundedBox(modifier = Modifier.padding(8.dp)) {
                Text(text = "RoundedBox")
            }
        },
    )
}
