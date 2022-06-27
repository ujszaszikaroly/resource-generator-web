package cz.pluscare.resourcegenerator.platform

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlatformSelector @Autowired constructor() {

    fun obtainCurrent(platformName: String): Platform? {
        return Platform::class.sealedSubclasses
            .firstOrNull { it.objectInstance?.name.equals(platformName, true) }
            ?.objectInstance
    }
}