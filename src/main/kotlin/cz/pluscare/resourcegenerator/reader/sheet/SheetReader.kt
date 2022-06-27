package cz.pluscare.resourcegenerator.reader.sheet

import cz.pluscare.resourcegenerator.logger.createLogger
import cz.pluscare.resourcegenerator.reader.FileReader
import cz.pluscare.resourcegenerator.reader.ResourceValue
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class SheetReader(private val sheetName: String?) : FileReader {

    private val connector = SheetConnector()

    private val logger = createLogger<SheetReader>()

    override fun getParsedValues(): List<ResourceValue> {
        return connector.getSheetInputStream()?.let {
            try {
                val values = mutableListOf<ResourceValue>()

                val workbook = XSSFWorkbook(it)

                sheetName?.let {
                    val sheet = workbook.getSheet(sheetName)
                    readSheetIntoValues(sheet, values)
                } ?: run {
                    for (i in 0 until workbook.numberOfSheets) {
                        val sheet = workbook.getSheetAt(i)
                        readSheetIntoValues(sheet, values)
                    }
                }
                values
            } catch (throwable: Throwable) {
                logger.error("Error while parsing sheet with ID: $sheetName")
                throwable.printStackTrace()
                emptyList()
            }
        } ?: emptyList()
    }

    private fun readSheetIntoValues(
        sheet: XSSFSheet,
        values: MutableList<ResourceValue>
    ) {
        val sheetIterator = sheet.iterator()

        while (sheetIterator.hasNext()) {

            val row = sheetIterator.next()

            if (!isRowEmpty(row)) {
                values.add(
                    ResourceValue(
                        id = row.getCell(0).getStringValue(),
                        comment = row.getCell(1).getStringValue(),
                        default = row.getCell(2).getStringValue(),
                        english = row.getCell(2).getStringValue(),
                        czech = row.getCell(3).getStringValue(),
                        slovak = row.getCell(4).getStringValue()
                    ).also { println(it) }
                )
            }
        }
    }

    private fun isRowEmpty(row: Row?): Boolean {
        if (row == null || row.lastCellNum <= 0) {
            return true
        }
        for (cellNum in row.firstCellNum until row.lastCellNum) {
            val cell: Cell? = row.getCell(cellNum)
            if (cell != null && cell.cellType !== CellType.BLANK && cell.toString().isNotBlank()) {
                return false
            }
        }
        return true
    }

    private fun Cell?.getStringValue(): String {
        return when (this?.cellType) {
            CellType.STRING -> stringCellValue.orEmpty().replaced()
            CellType.NUMERIC -> numericCellValue.toString().replaced()
            else -> ""
        }
    }

    private fun String.replaced(): String {
        return replace("&", "&amp;")
            .replace("\'", "\\'")
    }
}