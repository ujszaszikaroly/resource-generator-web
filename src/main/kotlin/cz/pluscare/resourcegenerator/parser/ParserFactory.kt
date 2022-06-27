package cz.pluscare.resourcegenerator.parser

import cz.pluscare.resourcegenerator.parser.lproj.LprojParser
import cz.pluscare.resourcegenerator.platform.Platform
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class ParserFactory @Autowired constructor() {

    @Autowired
    @Qualifier("XmlParser")
    private lateinit var xmlParser: ResourceParser

    @Autowired
    @Qualifier("LprojParser")
    private lateinit var lprojParser: LprojParser

    fun getByPlatform(platform: Platform): ResourceParser {
        return when (platform) {
            Platform.Android -> xmlParser
            Platform.Ios -> lprojParser
        }
    }

}