package cz.pluscare.resourcegenerator.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> createLogger() = LoggerFactory.getLogger(T::class.java)!!

class Log internal constructor(private val logger: Logger) {

    fun debug(message: String) = logger.debug(message)

    fun error(message: String) = logger.error(message)

    fun info(message: String) = logger.info(message)
}