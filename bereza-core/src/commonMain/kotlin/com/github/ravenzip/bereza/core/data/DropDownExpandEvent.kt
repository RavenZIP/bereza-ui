package com.github.ravenzip.bereza.core.data

sealed class DropDownExpandEvent {
    object Expanded : DropDownExpandEvent()

    data class Collapsed(val afterSelect: Boolean) : DropDownExpandEvent()

    companion object {
        fun DropDownExpandEvent.isExpanded(): Boolean = this is Expanded
    }
}

fun createDropDownExpandEvent(
    expanded: Boolean,
    afterSelect: Boolean = false,
): DropDownExpandEvent =
    if (expanded) DropDownExpandEvent.Expanded else DropDownExpandEvent.Collapsed(afterSelect)
