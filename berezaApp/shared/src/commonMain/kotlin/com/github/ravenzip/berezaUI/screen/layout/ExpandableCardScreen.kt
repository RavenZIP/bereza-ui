package com.github.ravenzip.berezaUI.screen.layout

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.core.components.layout.ExpandableCard
import com.github.ravenzip.berezaUI.screen.components.shared.ComponentScreen

@Composable
fun ExpandableCardScreen(navigationViewModel: RootNavigationViewModel) {
    val expanded = remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

    // TODO перейти на собственный LayoutScreen
    ComponentScreen(
        title = "ExpandableCard",
        description = "Card, который умеет скрывать свое содержимое по клику.",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            ExpandableCard(
                text = { Text("Заголовок") },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDownward,
                        contentDescription = "ExpandableCardArrowDownward",
                        modifier = Modifier.rotate(rotation.value),
                    )
                },
                onExpandedChange = { expanded.value = it },
            ) { padding ->
                Text(
                    modifier = Modifier.padding(padding),
                    text = "Этот текст должен скрываться",
                )
            }
        },
    )
}
