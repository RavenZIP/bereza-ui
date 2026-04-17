package com.github.ravenzip.berezaUI.reactive.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

@Stable
data class ComponentState<T>(
    val value: T,
    val enabled: Boolean,
    val errorState: ComponentErrorState,
)

fun <T> computeComponentState(
    value: T,
    status: FormControlStatus,
    dirty: Boolean,
    touched: Boolean,
): ComponentState<T> {
    val errorState =
        when (status) {
            FormControlStatus.Disabled,
            FormControlStatus.Valid -> {
                ComponentErrorState.Ok
            }

            is FormControlStatus.Invalid -> {
                // Не упадем, потому что в случае статуса Invalid текст ошибки должен быть всегда
                val errorMessage = status.errors.first().message

                if (dirty || touched) ComponentErrorState.Error(errorMessage)
                else ComponentErrorState.Ok
            }
        }

    return ComponentState(value = value, enabled = status.isEnabled(), errorState = errorState)
}

fun <T> MutableFormControl<T>.computeComponentState(): ComponentState<T> =
    computeComponentState(value = value, status = status, touched = touched, dirty = dirty)

// TODO добавить обработку жизненного цикла, по аналогии с collectAsState и
// collectAsStateWithLifeCycle
@Composable
fun <T> MutableFormControl<T>.collectComponentState(): State<ComponentState<T>> =
    produceState(this.computeComponentState(), this) {
        combine(valueChanges, statusChanges, touchedChanges, dirtyChanges) {
                value,
                status,
                touched,
                dirty ->
                computeComponentState(
                    value = value,
                    status = status,
                    touched = touched,
                    dirty = dirty,
                )
            }
            .distinctUntilChanged()
            .collect { x -> value = x }
    }
