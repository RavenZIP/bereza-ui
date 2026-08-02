package com.github.ravenzip.berezaUI.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
import com.github.ravenzip.berezaUI.data.Screen
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton

@Composable
fun HomeScreenGroup(
    title: String,
    description: String = "",
    content: LazyListScope.() -> Unit,
) {
    Card {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.W500)
                if (description.isNotBlank()) {
                    Text(text = description)
                }
            }

            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                content()
            }
        }
    }
}

@Composable
fun HomeScreenShortImportantInfo(name: String, value: String) {
    Card {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = name, fontWeight = FontWeight.W500)
            Text(text = value)
        }
    }
}

@Composable
fun HomeScreen(navigationViewModel: RootNavigationViewModel) {
    Column(
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = "Добро пожаловать в Bereza UI",
                    fontWeight = FontWeight.W500,
                    fontSize = 23.sp,
                )

                Text(
                    text =
                        "Библиотека компонентов для Compose Multiplatform, " +
                            "расширяющая возможности стандартной Material библиотеки"
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    item {
                        HomeScreenShortImportantInfo("Версия", "0.4.0")
                    }

                    item {
                        HomeScreenShortImportantInfo("Модули", "4")
                    }

                    item {
                        HomeScreenShortImportantInfo("Компоненты", "6")
                    }

                    item {
                        HomeScreenShortImportantInfo("Формы", "2")
                    }
                }
            }

            Card(
                modifier =
                    Modifier.weight(1.25f).clip(CardDefaults.shape).clickable {
                        navigationViewModel.navigateTo(Screen.LastChanges)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Text(
                        text = "Последние изменения",
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp,
                    )

                    Column {
                        Text(text = "Версия")
                        Text(text = "0.4.0")
                    }

                    Column {
                        Text(text = "Дата")
                        Text(text = "xx.xx.xxxx")
                    }

                    Text("Информация будет добавлена позднее")
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            HomeScreenGroup("Компоненты", "Демонстрация отдельных элементов интерфейса") {
                items(navigationViewModel.componentScreens) { screen ->
                    SimpleButton(
                        { navigationViewModel.navigateTo(screen) },
                        navigationViewModel.screenRouteToScreenName.getValue(screen),
                    )
                }
            }

            HomeScreenGroup("Формы", "Готовые примеры использования компонентов") {
                items(navigationViewModel.formScreens) { screen ->
                    SimpleButton(
                        { navigationViewModel.navigateTo(screen) },
                        navigationViewModel.formRouteToFormName.getValue(screen),
                    )
                }
            }

            HomeScreenGroup("Ссылки") {
                item {
                    SimpleButton({}, "GitHub")
                }

                item {
                    SimpleButton({}, "Telegram")
                }
            }
        }
    }
}
