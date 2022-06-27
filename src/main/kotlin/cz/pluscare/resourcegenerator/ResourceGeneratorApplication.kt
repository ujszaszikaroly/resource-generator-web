package cz.pluscare.resourcegenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResourceGeneratorApplication

fun main(args: Array<String>) {
    runApplication<ResourceGeneratorApplication>(*args)
}
