package cz.pluscare.resourcegenerator.extensions

fun emojiText(vararg values: Int): String {
    return java.lang.StringBuilder().apply {
        values.forEach {
            append(String(Character.toChars(it)))
        }
    }.toString()
}