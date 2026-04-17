package com.github.ravenzip.berezaUI.core.components.switch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun SwitchWithText(
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    Row(
        modifier = modifier.clip(shape).clickable { onClick() }.padding(padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        text()

        Spacer(modifier = Modifier.weight(1f))

        Switch(checked = selected, onCheckedChange = null, enabled = enabled, colors = colors)
    }
}
