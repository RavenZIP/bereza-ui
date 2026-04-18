package com.github.ravenzip.berezaUI.core.data

sealed class DropDownExpandEvent {
    object Expanded : DropDownExpandEvent()

    data class Collapsed(val afterSelect: Boolean) : DropDownExpandEvent()
}

fun createDropDownExpandEvent(
    expanded: Boolean,
    afterSelect: Boolean = false,
): DropDownExpandEvent =
    if (expanded) DropDownExpandEvent.Expanded else DropDownExpandEvent.Collapsed(afterSelect)
