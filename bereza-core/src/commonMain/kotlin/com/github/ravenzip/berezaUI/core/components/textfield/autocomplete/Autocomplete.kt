package com.github.ravenzip.berezaUI.core.components.textfield.autocomplete

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.TextFieldWithSupportingRow
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.berezaUI.core.data.DropDownExpandEvent.Companion.isExpanded
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldColors
import com.github.ravenzip.berezaUI.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.berezaUI.core.data.SourceState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Autocomplete — компонент с возможностью выбора элемента из списка, который фильтруется или
 * формируется по мере ввода текста. Ввод значения, отсутствующего в списке, не поддерживается.
 */

// TODO onClear должен чистить выбранный текст. Снаружи или внутри компонента?
// TODO сделать спиннер при загрузке (trailingIcon?)
// TODO сделать Outlined версию
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun <T> Autocomplete(
    modifier: Modifier = Modifier,
    selected: T? = null,
    displayWith: (T) -> String,
    onSelect: (T) -> Unit,
    search: (String) -> Flow<List<T>>,
    searchDebounce: Duration = 500.milliseconds,
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
    var inputText by remember { mutableStateOf("") }
    var sourceState by remember { mutableStateOf<SourceState<T>>(SourceState.Content(listOf())) }

    LaunchedEffect(displayWith, selected) {
        inputText = if (selected != null) displayWith(selected) else ""
    }

    LaunchedEffect(search) {
        val inputTextFlow = snapshotFlow {
            inputText
        }
            .debounce(searchDebounce)
            .distinctUntilChanged()

        val expandedFlow = snapshotFlow { expanded }

        inputTextFlow
            .combine(expandedFlow) { query, expanded -> query to expanded }
            .filter { (_, expanded) -> expanded }
            .onEach { sourceState = SourceState.Loading }
            .flatMapLatest { (query) -> search(query) }
            .onEach { response -> sourceState = SourceState.Content(response) }
            .launchIn(this)
    }

    DropDownTextFieldBox(
        sourceState = sourceState,
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
            // который предоставляет дефолтное поведение и снаружи получать строку
            // Либо снаружи получать emptyContent: @Composable () -> Unit

            Text(text = "Не найдено")
        },
        loadingContent = {
            Text(text = "Загрузка...")
        },
        enabled = enabled,
        shape = shape,
        colors = colors.menuColors,
    )
}
