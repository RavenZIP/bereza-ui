package com.github.ravenzip.berezaUI.core.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.DropDownTextFieldBox
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.TrailingContent
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.DropDownExpandEvent.Companion.isExpanded
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldColors
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.berezaUI.core.data.SourceState

/**
 * ComboBox — компонент с возможностью выбора элемента из заданного списка или ввода собственного
 * значения.
 */
// TODO сделать Outlined версию
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Combobox(
    source: List<T>,
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (T, String) -> Boolean,
    onAddItem: ((String) -> Unit)? = null,
    onClear: (() -> Unit)? = null,
    errorState: ComponentErrorState = ComponentErrorState.Ok,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    key: (T) -> Any? = { it },
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedItemText =
        remember(displayWith, selected) { if (selected != null) displayWith(selected) else "" }

    var inputText by remember(selectedItemText) { mutableStateOf(selectedItemText) }

    val filteredSource =
        remember(source, inputText, search) {
            source.filter { item ->
                search(item, inputText)
            }
        }

    DropDownTextFieldBox(
        sourceState = SourceState.Content(filteredSource),
        onSelectItem = onSelect,
        expanded = expanded,
        onExpandedChange = { event -> expanded = event.isExpanded() },
        modifier = modifier,
        key = key,
        textField = {
            TextFieldWithSupportingRow(
                value = inputText,
                onValueChange = { x -> inputText = x },
                modifier =
                    Modifier.menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = enabled,
                    ),
                errorState = errorState,
                onFocusChange = onFocusChange,
                onTouchChange = onTouchChange,
                maxLines = 1,
                singleLine = true,
                label = label,
                placeholder = placeholder,
                trailingIcon = { TrailingContent(selected, expanded, enabled, onClear) },
                shape = shape,
                colors = colors.textFieldColors,
            )
        },
        itemContent = { item ->
            val text = remember(item) { displayWith(item) }
            Text(text = text)
        },
        emptyContent = {
            // TODO нужно как-то дать возможность прокинуть свой контент
            // Возможно, что стоит поступить как с TrailingIcon в ExposedDropdownMenuBoxScope,
            // который предоставляет дефолтное поведение
            // Либо костяк оставить, а снаружи получать text: @Composable () -> Unit

            // TODO надо ли при нажатии добавить автоматически выбирать элемент? Если да,
            // тогда список с элементами, которые отображаются в выпадающем списке, должен храниться
            // на стороне компонента и будет состоять из исходного списка + того, что натыкал юзер
            // Либо же оставить это на откуп пользователю, захочет - реализует, после подстановки
            // в selected компонент сам отреагирует
            if (onAddItem != null) {
                TextButton(onClick = { onAddItem(inputText) }) {
                    Text("Добавить")
                }
            } else {
                Text(text = "Не найдено")
            }
        },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}

// TODO
@Composable fun MultiCombobox() {}
