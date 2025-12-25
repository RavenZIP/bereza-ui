package components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SupportRow(
    modifier: Modifier = Modifier,
    left: (@Composable () -> Unit)? = null,
    right: (@Composable () -> Unit)? = null,
) {
    if (left != null || right != null) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            if (left != null) {
                Box(Modifier.weight(1f)) { left() }
            }

            if (right != null) {
                Box(Modifier.weight(1f)) { right() }
            }
        }
    }
}
