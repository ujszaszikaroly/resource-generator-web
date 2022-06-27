package cz.pluscare.resourcegenerator.response

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class FileResponseCreator @Autowired constructor() {

    fun createOk(body: ByteArray, fileName: String): ResponseEntity<ByteArray> {
        val header = HttpHeaders().apply {
            set(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"$fileName\"")
        }
        return ResponseEntity.ok()
            .headers(header)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(body)
    }

    fun createBadRequest(): ResponseEntity<ByteArray> {
        return ResponseEntity.badRequest().build()
    }

    fun createNotAcceptable(): ResponseEntity<ByteArray> {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
    }
}