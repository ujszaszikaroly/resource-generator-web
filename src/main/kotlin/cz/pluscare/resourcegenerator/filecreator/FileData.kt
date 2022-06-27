package cz.pluscare.resourcegenerator.filecreator

import cz.pluscare.resourcegenerator.platform.FileType

class FileData(
    val fileName: String,
    val type: FileType,
    val bytes: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileData

        if (fileName != other.fileName) return false
        if (type != other.type) return false
        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }
}