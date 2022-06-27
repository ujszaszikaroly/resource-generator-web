package cz.pluscare.resourcegenerator.controller

import cz.pluscare.resourcegenerator.filecreator.FileData
import cz.pluscare.resourcegenerator.filecreator.ZipCreator
import cz.pluscare.resourcegenerator.parser.ParserService
import cz.pluscare.resourcegenerator.parser.asByteArray
import cz.pluscare.resourcegenerator.platform.Platform
import cz.pluscare.resourcegenerator.reader.FileReader
import cz.pluscare.resourcegenerator.reader.Language
import cz.pluscare.resourcegenerator.reader.sheet.SheetReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourcesService @Autowired constructor() {

    @Autowired
    private lateinit var parserService: ParserService

    @Autowired
    private lateinit var zipCreator: ZipCreator

    fun run(currentPlatform: Platform, sheetName: String?): ResourceResponse? {
        val files = mutableListOf<FileData>()
        val reader: FileReader = SheetReader(sheetName)
        val service = parserService.withReader(reader)
        Language.values().forEach {
            service.getParsedText(currentPlatform, it)?.asByteArray()?.let { bytes ->
                val folderName = currentPlatform.getFullFileName(it)
                files.add(FileData(folderName, currentPlatform.fileType, bytes))
            }
        }
        return if (files.isEmpty()) null
        else {
            val zippedFile = zipCreator.compress(*files.toTypedArray()).toByteArray()
            ResourceResponse(zippedFile, "resources.zip")
        }
    }

}