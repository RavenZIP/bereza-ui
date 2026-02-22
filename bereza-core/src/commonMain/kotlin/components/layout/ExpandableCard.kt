package components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCard(
    text: @Composable (() -> Unit),
    icon: @Composable (() -> Unit),
    onExpandedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(10.dp),
    shape: Shape = CardDefaults.shape,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    collapsedContent: @Composable ((contentPadding: PaddingValues) -> Unit),
) {
    val expanded = rememberSaveable { mutableStateOf(false) }

    Card(modifier = modifier, colors = colors, elevation = elevation, border = border) {
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Row(
                Modifier.clip(shape)
                    .clickable {
                        expanded.value = !expanded.value
                        onExpandedChange(expanded.value)
                    }
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                text()

                icon()
            }

            AnimatedVisibility(visible = expanded.value) { collapsedContent(contentPadding) }
        }
    }
}
