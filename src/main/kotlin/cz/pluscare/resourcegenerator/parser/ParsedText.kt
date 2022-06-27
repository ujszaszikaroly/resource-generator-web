package cz.pluscare.resourcegenerator.parser

interface ParsedText {
    var value: String
}

fun ParsedText?.asByteArray(): ByteArray =
    this?.value?.toByteArray(Charsets.UTF_8) ?: ByteArray(0)