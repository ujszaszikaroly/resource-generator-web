package cz.pluscare.resourcegenerator.platform

import cz.pluscare.resourcegenerator.reader.Language

sealed interface Platform {

    val name: String
    val filename: String
    val fileType: FileType

    object Android : Platform {
        override val name: String = "Android"
        override val filename: String = "strings"
        override val fileType: FileType = FileType.XML
    }

    object Ios : Platform {
        override val name: String = "iOS"
        override val filename: String = "Localizable"
        override val fileType: FileType = FileType.STRINGS
    }

    fun folderName(language: Language): String {
        return if (this == Android) {
            when (language) {
                Language.DEFAULT -> "values"
                else -> "values-${language.code}"
            }
        } else {
            when (language) {
                Language.DEFAULT -> "Base.lproj"
                else -> "${language.code}.lproj"
            }
        }
    }

    fun getFullFileName(language: Language): String =
        "${folderName(language)}/$filename"

}