package style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
class IconStyle(val size: Dp = 25.dp, val color: Color? = null) {
    companion object {
        /**
         * [Default]
         *
         * [size] - 25
         *
         * [color] - null
         */
        @Stable val Default = IconStyle()

        /**
         * [Size20]
         *
         * [size] - 20
         *
         * [color] - null
         */
        @Stable val Size20 = IconStyle(size = 20.dp)

        /**
         * [Size22]
         *
         * [size] - 22
         *
         * [color] - null
         */
        @Stable val Size22 = IconStyle(size = 22.dp)

        /**
         * [PrimaryColor]
         *
         * [size] - 25
         *
         * [color] - MaterialTheme.colorScheme.primary
         */
        @Stable
        val PrimaryColor
            @Composable get() = IconStyle(color = MaterialTheme.colorScheme.primary)
    }
}
