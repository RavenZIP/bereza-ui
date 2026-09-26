package com.github.ravenzip.bereza.app.screen.layout

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
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.bereza.app.screen.components.shared.ComponentScreen
import com.github.ravenzip.bereza.core.components.layout.ExpandableCard

@Composable
fun ExpandableCardScreen(navigationViewModel: RootNavigationViewModel) {
    val expanded = remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(targetValue = if (expanded.value) 180f else 0f)

    // TODO перейти на собственный LayoutScreen
    ComponentScreen(
        title = "ExpandableCard",
        description = "Card, который умеет скрывать свое содержимое по клику.",
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
