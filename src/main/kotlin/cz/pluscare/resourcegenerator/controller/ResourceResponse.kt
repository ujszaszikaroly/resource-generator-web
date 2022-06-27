package cz.pluscare.resourcegenerator.controller

data class ResourceResponse(
    val file: ByteArray,
    val fileName: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceResponse

        if (!file.contentEquals(other.file)) return false
        if (fileName != other.fileName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = file.contentHashCode()
        result = 31 * result + fileName.hashCode()
        return result
    }
}