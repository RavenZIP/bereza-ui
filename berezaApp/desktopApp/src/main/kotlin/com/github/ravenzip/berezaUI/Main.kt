package com.github.ravenzip.berezaUI

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state =
            rememberWindowState(
                position = WindowPosition(Alignment.Center),
                size = DpSize(1000.dp, 720.dp),
            ),
        title = "Bereza UI",
    ) {
        App()
    }
}
