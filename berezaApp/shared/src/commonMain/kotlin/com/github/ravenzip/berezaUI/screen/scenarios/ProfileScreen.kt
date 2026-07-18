package com.github.ravenzip.berezaUI.screen.scenarios

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton

@Composable
fun ProfileScreen(navigationViewModel: RootNavigationViewModel) {
    Column {
        Text("Тут должен быть пример экрана профиля пользователя")
        SimpleButton({ navigationViewModel.navigateBack() }, "Назад")
    }
}
