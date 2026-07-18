package com.github.ravenzip.berezaUI.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton

@Composable
fun TextFieldScreen(navigationViewModel: RootNavigationViewModel) {
    Column {
        Text("Тут должны быть текстовые поля")
        SimpleButton({ navigationViewModel.navigateBack() }, "Назад")
    }
}
