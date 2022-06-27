package cz.pluscare.resourcegenerator.parser.xml

import cz.pluscare.resourcegenerator.parser.ParsedText

class XmlText(override var value: String) : ParsedText {
    companion object {
        fun empty(): XmlText = XmlText("")
    }
}