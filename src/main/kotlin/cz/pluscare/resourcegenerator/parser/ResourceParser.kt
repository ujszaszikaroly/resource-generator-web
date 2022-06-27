package cz.pluscare.resourcegenerator.parser

import cz.pluscare.resourcegenerator.reader.Language
import cz.pluscare.resourcegenerator.reader.ResourceElement

interface ResourceParser {

    fun getParsedText(language: Language, values: List<ResourceElement>): ParsedText?
}