package com.github.ravenzip.berezaUI.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton

@Composable
fun LastChangesScreen(navigationViewModel: RootNavigationViewModel) {
    Column {
        Text("Тут должен быть список изменений последней версии")
        SimpleButton({ navigationViewModel.navigateBack() }, "Назад")
    }
}
