package cz.pluscare.resourcegenerator.controller

import cz.pluscare.resourcegenerator.platform.PlatformSelector
import cz.pluscare.resourcegenerator.response.FileResponseCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@RestController
@Component
class ResourcesController {

    @Autowired
    private lateinit var selector: PlatformSelector

    @Autowired
    private lateinit var responseCreator: FileResponseCreator

    @Autowired
    private lateinit var service: ResourcesService

    @GetMapping("/resources/{platform}")
    @ResponseBody
    fun getCompressedResources(
        @PathVariable platform: String,
        @RequestParam sheetName: String?
    ): ResponseEntity<ByteArray> {
        return selector.obtainCurrent(platform)?.let { currentPlatform ->
            service.run(currentPlatform, sheetName)?.let {
                responseCreator.createOk(it.file, it.fileName)
            } ?: responseCreator.createNotAcceptable()
        } ?: responseCreator.createBadRequest()
    }

}