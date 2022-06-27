package cz.pluscare.resourcegenerator.parser.lproj

import cz.pluscare.resourcegenerator.parser.ParsedText

class LprojText(override var value: String) : ParsedText {
    companion object {
        fun empty(): LprojText = LprojText("")
    }
}