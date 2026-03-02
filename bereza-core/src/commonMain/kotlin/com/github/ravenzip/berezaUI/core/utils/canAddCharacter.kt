package com.github.ravenzip.berezaUI.core.utils

internal fun canAddCharacter(currentLength: Int, maxLength: Int?): Boolean {
    return maxLength == null || currentLength <= maxLength
}
