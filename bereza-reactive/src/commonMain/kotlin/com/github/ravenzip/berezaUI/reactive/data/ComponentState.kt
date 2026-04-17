package com.github.ravenzip.berezaUI.reactive.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError
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
    errors: List<ValidationError>,
): ComponentState<T> {
    val errorMessage = errors.firstOrNull()?.message ?: ""
    val errorState =
        computeComponentErrorState(
            touched = touched,
            dirty = dirty,
            invalid = status.isInvalid(),
            error = errorMessage,
        )

    return ComponentState(value = value, enabled = status.isEnabled(), errorState = errorState)
}

fun <T> MutableFormControl<T>.computeComponentState(): ComponentState<T> =
    computeComponentState(value, status, touched = touched, dirty = dirty, errors = errors)

fun computeComponentErrorState(
    touched: Boolean,
    dirty: Boolean,
    invalid: Boolean,
    error: String,
): ComponentErrorState =
    if (invalid && (dirty || touched)) ComponentErrorState.Error(error) else ComponentErrorState.Ok

// TODO добавить обработку жизненного цикла, по аналогии с collectAsState и
// collectAsStateWithLifeCycle
@Composable
fun <T> MutableFormControl<T>.collectComponentState(): State<ComponentState<T>> =
    produceState(this.computeComponentState(), this) {
        combine(valueChanges, statusChanges, touchedChanges, dirtyChanges, errorsChanges) {
                value,
                status,
                touched,
                dirty,
                errors ->
                computeComponentState(
                    value = value,
                    status = status,
                    touched = touched,
                    dirty = dirty,
                    errors = errors,
                )
            }
            .distinctUntilChanged()
            .collect { x -> value = x }
    }
