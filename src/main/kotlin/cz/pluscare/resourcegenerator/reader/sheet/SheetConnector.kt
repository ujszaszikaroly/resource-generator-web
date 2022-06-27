package cz.pluscare.resourcegenerator.reader.sheet

import cz.pluscare.resourcegenerator.logger.createLogger
import java.io.InputStream
import java.net.URL

class SheetConnector {

    private val logger = createLogger<SheetConnector>()

    fun getSheetInputStream(): InputStream? {
        return try {
            URL(FILE_URL).openConnection().getInputStream()
        } catch (throwable: Throwable) {
            logger.error(throwable.message)
            null
        }
    }

    companion object {
        private const val FILE_URL =
            "https://docs.google.com/spreadsheets/d/e/2PACX-1vSuHVknhzWyAh7NkVhLPLw7zbjVSWGRNVZ9p7a49mgD637YFTqYKC1PXlCPfxjlSqvEPZ7rS6WcM1hH/pub?output=xlsx"
    }


}