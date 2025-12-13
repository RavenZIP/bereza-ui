package components.utils

internal fun canAddCharacter(currentLength: Int, maxLength: Int?): Boolean {
    return maxLength == null || currentLength < maxLength
}
