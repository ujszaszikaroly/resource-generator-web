package cz.pluscare.resourcegenerator.filecreator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Component
class ZipCreator @Autowired constructor() {

    fun compress(vararg files: FileData): ByteArrayOutputStream {
        val outputStream = ByteArrayOutputStream()
        val zipOutputStream = ZipOutputStream(outputStream)
        files.forEach {
            val zipEntry = ZipEntry(it.fileName + it.type.postfix)
            zipOutputStream.putNextEntry(zipEntry)
            zipOutputStream.write(it.bytes)
            zipOutputStream.closeEntry()
        }
        zipOutputStream.close()
        return outputStream
    }
}