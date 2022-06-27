package cz.pluscare.resourcegenerator.reader

interface FileReader {

    fun getParsedValues(): List<ResourceValue>
}