package components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SupportRow(
    modifier: Modifier = Modifier,
    left: (@Composable () -> Unit)? = null,
    right: (@Composable () -> Unit)? = null,
) {
    if (left != null || right != null) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Box(Modifier.weight(1f)) { left?.invoke() }

            Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) { right?.invoke() }
        }
    }
}
