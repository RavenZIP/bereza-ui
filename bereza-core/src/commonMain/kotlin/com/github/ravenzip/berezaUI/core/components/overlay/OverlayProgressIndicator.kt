package com.github.ravenzip.berezaUI.core.components.overlay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun OverlayProgressIndicator(
    visible: Boolean,
    withCard: Boolean = false,
    maxCardSize: Dp = 170.dp,
    text: (@Composable (ColumnScope.() -> Unit))? = null,
    containerPadding: PaddingValues = PaddingValues(15.dp),
    contentSpacing: Dp = 15.dp,
    shape: Shape = RoundedCornerShape(14.dp),
) {
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.zIndex(1f),
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier =
                Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)).pointerInput(
                    Unit
                ) {
                    detectTapGestures {}
                },
            contentAlignment = Alignment.Center,
        ) {
            if (withCard) {
                Card(
                    modifier =
                        Modifier.sizeIn(maxHeight = maxCardSize, maxWidth = maxCardSize)
                            .aspectRatio(1f),
                    shape = shape,
                ) {
                    Column(
                        modifier = Modifier.padding(containerPadding).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularWavyProgressIndicator()

                        if (text != null) {
                            Spacer(modifier = Modifier.height(contentSpacing))
                            text()
                        }
                    }
                }
            } else {
                CircularWavyProgressIndicator()
            }
        }
    }
}
