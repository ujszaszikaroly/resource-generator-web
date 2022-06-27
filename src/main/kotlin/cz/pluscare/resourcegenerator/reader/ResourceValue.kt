package cz.pluscare.resourcegenerator.reader

import cz.pluscare.resourcegenerator.extensions.dropFirst

data class ResourceValue(
    val id: String,
    val comment: String,
    val default: String,
    val english: String,
    val czech: String,
    val slovak: String
)

fun List<ResourceValue>.toElements(language: Language): List<ResourceElement> {
    return map {
        val value = when (language) {
            Language.ENGLISH -> it.english
            Language.CZECH -> it.czech
            Language.SLOVAK -> it.slovak.orDefault(it.english)
            else -> it.english
        }
        ResourceElement(it.id, it.comment, value)
    }.dropFirst()
}

fun String?.orDefault(defaultValue: String): String {
    return if (this.isNullOrEmpty()) defaultValue else this
}