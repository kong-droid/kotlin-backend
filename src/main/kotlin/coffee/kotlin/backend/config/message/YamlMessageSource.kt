package coffee.kotlin.backend.config.message

import dev.akkinoc.util.YamlResourceBundle
import org.springframework.context.support.ResourceBundleMessageSource
import java.util.*

class YamlMessageSource: ResourceBundleMessageSource() {

    override fun doGetBundle(basename: String, locale: Locale): ResourceBundle {
        return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control)
    }
}