package com.github.ravenzip.berezaUI.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.berezaUI.extensions.components.SimpleButton

// Это должно быть общей оберткой для экранов с компонентами
// Для экранов с формами мооожет быть будет такая же, но это выясниться позже
// TODO сделать позже и поэтапно реализуя каждый параметр
// @Composable
// fun ComponentScreen(
//    title: String,
//    description: String,
//    gallery: @Composable () -> Unit,
//    playground: @Composable () -> Unit,
//    api: Map<String, String>,
//    usedIn: @Composable () -> Unit,
// ) {
//    LazyColumn(
//        modifier = Modifier.padding(10.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp),
//    ) {
//        item {
//            Column(
//                modifier = Modifier.padding(10.dp),
//                verticalArrangement = Arrangement.spacedBy(10.dp),
//            ) {
//                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.W500)
//                Text(description)
//            }
//        }
//
//        item {
//            Column(
//                modifier = Modifier.padding(10.dp),
//                verticalArrangement = Arrangement.spacedBy(10.dp),
//            ) {
//                Text(text = "Внешний вид", fontSize = 20.sp, fontWeight = FontWeight.W500)
//
//                gallery()
//            }
//        }
//
//        item {
//            Card {
//                Column(
//                    modifier = Modifier.padding(10.dp),
//                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                ) {
//                    Text(text = "Песочница", fontSize = 20.sp, fontWeight = FontWeight.W500)
//                    playground()
//                }
//            }
//        }
//
//        item {
//            Card {
//                Column(
//                    modifier = Modifier.padding(10.dp),
//                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                ) {
//                    Text(text = "API", fontSize = 20.sp, fontWeight = FontWeight.W500)
//                    api.entries.forEach { x ->
//                        key(x.key) {
//                            Text(x.key)
//
//                            Text(x.value)
//                        }
//                    }
//                }
//            }
//        }
//
//        item {
//            Card {
//                Column(
//                    modifier = Modifier.padding(10.dp),
//                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                ) {
//                    Text(text = "Используется в", fontSize = 20.sp, fontWeight = FontWeight.W500)
//                    usedIn()
//                }
//            }
//        }
//    }
// }

@Composable
fun ComponentScreen(
    title: String,
    description: String,
    hasIntegrationWithReactiveForms: Boolean = true,
    goBack: () -> Unit,
    content: @Composable () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        item {
            ComponentScreenGroup {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.W500)
                Text(description)
                Text(
                    text =
                        "Интеграция с Kotlin Reactive Forms: ${if (hasIntegrationWithReactiveForms) "Есть" else "Отсутствует"}"
                )
            }
        }

        item {
            ComponentScreenGroup {
                Text(text = "Примеры", fontWeight = FontWeight.W600)
                content()
            }
        }

        item {
            SimpleButton(onClick = goBack, text = "Назад")
        }
    }
}

@Composable
fun ComponentScreenGroup(content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        content()
    }
}
