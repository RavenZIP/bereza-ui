package com.github.ravenzip.berezaUI.reactive.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.isEnabled
import com.github.ravenzip.kotlinreactiveforms.form.FormControl
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

fun <T> FormControl<T>.computeComponentState(): ComponentState<T> =
    computeComponentState(value = value, status = status, touched = touched, dirty = dirty)

@Composable
fun <T> FormControl<T>.collectAsComponentState(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<ComponentState<T>> = collectAsComponentState(lifecycleOwner.lifecycle, minActiveState)

/**
 * Согласно документации, с недавнего времени Lifecycle появился и на остальных платформах, кроме
 * Android. Поэтому реализовал такой же механизм, как и в collectAsStateWithLifecycle, но без
 * прокидывания контекста корутин и начального значения
 *
 * @see [https://kotlinlang.org/docs/multiplatform/compose-lifecycle.html]
 * @see [androidx.lifecycle.compose.collectAsStateWithLifecycle]
 */
@Composable
fun <T> FormControl<T>.collectAsComponentState(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<ComponentState<T>> =
    produceState(this.computeComponentState(), this) {
        lifecycle.repeatOnLifecycle(minActiveState) {
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
    }
