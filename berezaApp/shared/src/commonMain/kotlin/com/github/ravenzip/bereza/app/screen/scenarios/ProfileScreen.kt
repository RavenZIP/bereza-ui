package com.github.ravenzip.bereza.app.screen.scenarios

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.ravenzip.bereza.app.RootNavigationViewModel
import com.github.ravenzip.compose.material3.SimpleButton

@Composable
fun ProfileScreen(navigationViewModel: RootNavigationViewModel) {
    Column {
        Text("Тут должен быть пример экрана профиля пользователя")
        SimpleButton({ navigationViewModel.navigateBack() }, "Назад")
    }
}
