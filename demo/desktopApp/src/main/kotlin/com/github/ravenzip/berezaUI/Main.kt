package com.github.ravenzip.berezaUI

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.ravenzip.bereza.ui.com.github.ravenzip.berezaUI.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "BerezaUI") { App() }
}
