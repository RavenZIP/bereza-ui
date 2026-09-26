package com.github.ravenzip.bereza.app.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.compose.material3.SimpleButton

// TODO надо ли?
@Composable
fun LastChangesScreen(navigationViewModel: RootNavigationViewModel) {
    Column {
        Text("Тут должен быть список изменений последней версии")
        SimpleButton({ navigationViewModel.navigateBack() }, "Назад")
    }
}
