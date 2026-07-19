package com.github.ravenzip.berezaUI.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun HomeScreen(navigationViewModel: RootNavigationViewModel) {
    Column(
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Text(
            text = "Добро пожаловать в BerezaUI",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.W500,
            fontSize = 25.sp,
        )

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Card {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Text(text = "Компоненты", fontSize = 18.sp)

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(navigationViewModel.componentScreens) { screen ->
                            SimpleButton(
                                { navigationViewModel.navigateTo(screen) },
                                navigationViewModel.screenRouteToScreenName.getValue(screen),
                            )
                        }
                    }
                }
            }

            Card {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Text(text = "Примеры экранов с компонентами", fontSize = 18.sp)

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(navigationViewModel.formScreens) { screen ->
                            SimpleButton(
                                { navigationViewModel.navigateTo(screen) },
                                navigationViewModel.formRouteToFormName.getValue(screen),
                            )
                        }
                    }
                }
            }
        }
    }
}
