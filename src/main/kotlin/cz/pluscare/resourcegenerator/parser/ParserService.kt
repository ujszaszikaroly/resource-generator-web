package cz.pluscare.resourcegenerator.parser

import cz.pluscare.resourcegenerator.platform.Platform
import cz.pluscare.resourcegenerator.reader.FileReader
import cz.pluscare.resourcegenerator.reader.Language
import cz.pluscare.resourcegenerator.reader.toElements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ParserService @Autowired constructor() {

    @Autowired
    private lateinit var parserFactory: ParserFactory

    private lateinit var reader: FileReader

    fun withReader(reader: FileReader): ParserService {
        return apply { this.reader = reader }
    }

    fun getParsedText(platform: Platform, language: Language): ParsedText? {
        val contentList = reader.getParsedValues()
        val parser = parserFactory.getByPlatform(platform)
        val elements = contentList.toElements(language)
        return parser.getParsedText(language, elements)
    }
}