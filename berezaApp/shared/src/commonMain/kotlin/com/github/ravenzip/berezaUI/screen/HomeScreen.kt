package com.github.ravenzip.berezaUI.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.RootNavigationViewModel
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
                Text(text = title, fontSize = 20.sp)
                Text(text = description)
            }

            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                content()
            }
        }
    }
}

@Composable
fun HomeScreen(navigationViewModel: RootNavigationViewModel) {
    Column(
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Row {
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

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Card {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Версия: ")
                            Text(text = "0.4.0")
                        }
                    }

                    Card {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Модулей: ")
                            Text(text = "4")
                        }
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Card {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Компонентов: ")
                            Text(text = "6")
                        }
                    }

                    Card {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Пользовательских форм: ")
                            Text(text = "2")
                        }
                    }
                }
            }

            Card(modifier = Modifier.weight(1f)) {
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
