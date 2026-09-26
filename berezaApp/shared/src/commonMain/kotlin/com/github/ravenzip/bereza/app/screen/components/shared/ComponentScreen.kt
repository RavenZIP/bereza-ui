package com.github.ravenzip.bereza.app.screen.components.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.compose.material3.SimpleButton

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
            }
        }

        item {
            ComponentScreenGroup {
                Text(text = "Примеры", fontWeight = FontWeight.W600)
                content()
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            SimpleButton(onClick = goBack, text = "Назад")
        }
    }
}

// TODO будет ли отличаться? Может стоит перейти на общую функцию,
//  если нет отличий (но тогда скорее всего придется убирать LazyColumn из ComponentScreen)
@Composable
fun LayoutScreen(
    title: String,
    description: String,
    goBack: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        ComponentScreenGroup {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.W500)
            Text(description)
        }

        ComponentScreenGroup {
            Text(text = "Примеры", fontWeight = FontWeight.W600)
            content()
        }

        SimpleButton(onClick = goBack, text = "Назад")
    }
}

@Composable
private fun ComponentScreenGroup(content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        content()
    }
}
