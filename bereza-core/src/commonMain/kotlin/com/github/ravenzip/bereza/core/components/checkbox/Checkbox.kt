package com.github.ravenzip.bereza.core.components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Checkbox(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier =
            modifier.clip(shape).clickable(enabled = enabled, onClick = onClick).padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Checkbox(checked = selected, onCheckedChange = null, enabled = enabled, colors = colors)

        content()
    }
}
